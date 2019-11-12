package self.com.githubtrendinglistapp.viewmodel


import org.junit.Assert
import org.junit.Test
import org.koin.core.inject

import self.com.githubtrendinglistapp.BaseTest
import self.com.githubtrendinglistapp.LocalCache

class MainViewModelTest :BaseTest(){
    private val viewModel: MainViewModel by inject()
    @Test
    fun `fetch trending github list from mock data is not null or empty`(){
    viewModel.fetchTrendingList({
        Assert.assertTrue(it != null)
        Assert.assertTrue(it?.isNotEmpty()?:false)
    },{
        assert(false)
    })
    }

    @Test
    fun `fetch trending github list from mock data size is two`(){
        viewModel.fetchTrendingList({
            Assert.assertTrue(it?.size==2)
        },{
            assert(false)
        })
    }

    @Test
    fun `fetch trending github list from mock data check Values`(){
        viewModel.fetchTrendingList({
            Assert.assertTrue(it?.get(0)?.author  =="tootsuite")
            Assert.assertTrue(it?.get(0)?.language  =="Ruby")


            Assert.assertTrue(it?.get(1)?.author  =="AceLewis")
            Assert.assertTrue(it?.get(1)?.language  =="Python")
        },{
            assert(false)
        })
    }

    @Test
    fun `fetch trending github list from mock data check Local cache`(){
        viewModel.fetchTrendingList({
            Assert.assertTrue(!LocalCache.repositoriesList.isNullOrEmpty())
            Assert.assertTrue(LocalCache.repositoriesList?.size==2)


            Assert.assertTrue(LocalCache.repositoriesList?.get(1)?.author  =="AceLewis")
            Assert.assertTrue(LocalCache.repositoriesList?.get(1)?.language  =="Python")
        },{
            assert(false)
        })
    }
}