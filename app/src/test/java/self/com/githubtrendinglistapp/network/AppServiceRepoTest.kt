package self.com .githubtrendinglistapp.network

import com.android.rahul.movies.network.AppServiceRepo
import org.junit.Assert
import org.junit.Test
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import self.com.githubtrendinglistapp.BaseTest
import self.com.githubtrendinglistapp.ServiceType

class AppServiceRepoTest: BaseTest(){
    val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.MOCK) }

    @Test
    fun `get trending latest repository is not null or empty`(){
        appServiceRepo.getRepositoriesList({
            Assert.assertTrue(it != null)
            Assert.assertTrue(it?.isNotEmpty()?:false)
        },{
            assert(false)
        })
    }

    @Test
    fun `get trending latest repository size is 2`(){
        appServiceRepo.getRepositoriesList({
            Assert.assertTrue(it?.size==2)
        },{
            assert(false)
        })
    }

    @Test
    fun `get trending latest repository check Values`(){
        appServiceRepo.getRepositoriesList({
            Assert.assertTrue(it?.get(0)?.author  =="tootsuite")
            Assert.assertTrue(it?.get(0)?.language  =="Ruby")


            Assert.assertTrue(it?.get(1)?.author  =="AceLewis")
            Assert.assertTrue(it?.get(1)?.language  =="Python")
        },{
            assert(false)
        })
    }
}