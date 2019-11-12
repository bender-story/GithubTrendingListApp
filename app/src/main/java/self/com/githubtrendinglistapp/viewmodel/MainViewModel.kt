package self.com.githubtrendinglistapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rahul.movies.network.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import self.com.githubtrendinglistapp.LocalCache
import self.com.githubtrendinglistapp.ServiceType
import self.com.githubtrendinglistapp.datamodel.Repositories
import self.com.githubtrendinglistapp.Constants.twoHoursPeriod
import java.util.*


class MainViewModel : ViewModel(),KoinComponent {
    val trendingList: MutableLiveData<List<Repositories>?> = MutableLiveData()
    val appServiceRepo: AppServiceRepo by inject{parametersOf(ServiceType.API)}
    var timer = Timer()
//    var viewState:ViewState=ViewState.LOADER
//    var showError=ObservableBoolean(false)
//    var showLoader=ObservableBoolean(false)

    // call service to fetch movie list and update the mutable list.
    fun  fetchTrendingList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onSuccess.invoke()
        appServiceRepo.getRepositoriesList({ response ->
            trendingList.postValue(response)
            LocalCache.repositoriesList=response
            callServiceEveryTwoHours(onSuccess)
        }, {
            onError.invoke(it)
        })
    }

    fun getMainRowViewModel(): List<MainRowViewModel>? {
       return trendingList.value?.map {
            MainRowViewModel(it)
        }
    }

    fun callServiceEveryTwoHours( execute: () -> Unit){
        timer.scheduleAtFixedRate(
            object : TimerTask() {

                override fun run() {
                    AppServiceRepo(ServiceType.API).getRepositoriesList({
                        LocalCache.repositoriesList=it
                    },{})
                }
            },
            twoHoursPeriod, twoHoursPeriod
        )
    }
}

 enum class ViewState{
     LOADER,
     ERROR,
     SHOW_LIST
 }