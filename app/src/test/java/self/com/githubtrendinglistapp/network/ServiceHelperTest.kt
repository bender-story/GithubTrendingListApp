package self.com.githubtrendinglistapp.network

import com.android.rahul.movies.network.ServiceInterface
import org.junit.Assert
import org.junit.Test
import self.com.githubtrendinglistapp.BaseTest
import self.com.githubtrendinglistapp.ServiceType
import java.util.*

class ServiceHelperTest :BaseTest(){

    @Test
    fun `check if instance of ServiceInterface is SimpleInterface`(){
        var serviceAPIHelper=ServiceAPIHelper(ServiceType.API)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is ServiceInterface)
    }

    @Test
    fun `check if instance of ServiceInterface is mockServiceImpl`(){
        var serviceAPIHelper=ServiceAPIHelper(ServiceType.MOCK)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is MockServiceImpl)
    }
}