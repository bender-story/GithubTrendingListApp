package self.com.githubtrendinglistapp.di

import com.android.rahul.movies.network.AppServiceRepo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import self.com.githubtrendinglistapp.ServiceType
import self.com.githubtrendinglistapp.network.ServiceAPIHelper
import self.com.githubtrendinglistapp.viewmodel.MainViewModel

val appModule= module {
    factory { (serviceType : ServiceType) -> AppServiceRepo(serviceType) }
    factory { (serviceType : ServiceType) -> ServiceAPIHelper(serviceType) }
    viewModel { MainViewModel() }
}