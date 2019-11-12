package self.com.githubtrendinglistapp.network

import com.android.rahul.movies.network.NetworkAPIController
import com.android.rahul.movies.network.ServiceInterface
import self.com.githubtrendinglistapp.Constants
import self.com.githubtrendinglistapp.ServiceType

class ServiceAPIHelper(serviceType: ServiceType){
    private var serviceInterface: ServiceInterface?=null
    init {
        when(serviceType) {
            ServiceType.API->
                serviceInterface =
                    NetworkAPIController.getApiClient(Constants.NEWS_API_BASE_URL)?.create(ServiceInterface::class.java)
            else-> NetworkAPIController.getApiClient(Constants.NEWS_API_BASE_URL)?.create(MockServiceImpl::class.java)
        }
    }

    fun getServiceinterface(): ServiceInterface? {
        return serviceInterface
    }
}