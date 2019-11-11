package com.android.rahul.movies.network

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers
import self.com.githubtrendinglistapp.Constants.NEWS_API_BASE_URL
import self.com.githubtrendinglistapp.ServiceType
import self.com.githubtrendinglistapp.datamodel.Repositories
import self.com.githubtrendinglistapp.network.MockServiceImpl

class AppServiceRepo(serviceType: ServiceType){
    private var serviceInterface:ServiceInterface?=null
    init {
        when(serviceType) {
            ServiceType.API->
            serviceInterface =
                NetworkAPIController.getApiClient(NEWS_API_BASE_URL)?.create(ServiceInterface::class.java)
            else->NetworkAPIController.getApiClient(NEWS_API_BASE_URL)?.create(MockServiceImpl::class.java)
        }
    }
    // gets the repository list from service
    fun getRepositoriesList(onSuccess: (Repositories) -> Unit,
                     onError: (String) -> Unit){

        serviceInterface!!.fetchTrendingRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }
}


