package self.com.githubtrendinglistapp.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.rahul.movies.network.AppServiceRepo
import self.com.githubtrendinglistapp.ServiceType
import self.com.githubtrendinglistapp.datamodel.Repositories

class MainViewModel : ViewModel() {
    val trendingList: MutableLiveData<List<Repositories>?> = MutableLiveData()
    private val appServiceRepo = AppServiceRepo(ServiceType.API)
//    var viewState:ViewState=ViewState.LOADER
//    var showError=ObservableBoolean(false)
//    var showLoader=ObservableBoolean(false)

    // call service to fetch movie list and update the mutable list.
    fun fetchTrendingList(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        onSuccess.invoke()
        appServiceRepo.getRepositoriesList({ response ->
            trendingList.postValue(response)

        }, {
            onError.invoke(it)
        })
    }

    fun getMainRowViewModel(): List<MainRowViewModel>? {
       return trendingList.value?.map {
            MainRowViewModel(it)
        }
    }
}

 enum class ViewState{
     LOADER,
     ERROR,
     SHOW_LIST
 }