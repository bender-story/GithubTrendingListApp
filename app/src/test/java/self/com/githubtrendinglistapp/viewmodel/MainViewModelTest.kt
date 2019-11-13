package self.com.githubtrendinglistapp.viewmodel


import org.junit.Assert
import org.junit.Test
import org.koin.core.inject

import self.com.githubtrendinglistapp.BaseTest
import self.com.githubtrendinglistapp.LocalCache
import self.com.githubtrendinglistapp.datamodel.Repositories

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


            Assert.assertEquals("AceLewis",LocalCache.repositoriesList?.get(1)?.author)
            Assert.assertEquals("Python",LocalCache.repositoriesList?.get(1)?.language )
        },{
            assert(false)
        })
    }

    @Test
    fun `getMainRowViewModel is not null or empty`(){
        viewModel.fetchTrendingList({},{})
       var list= viewModel.getMainRowViewModel()
        Assert.assertTrue(!list.isNullOrEmpty())
        Assert.assertTrue(list?.size==2)

    }

    @Test
    fun `getMainRowViewModel has values`(){
        viewModel.trendingList.postValue(listOf(Repositories("AceLewis","", listOf(),0,"",0,"Python","","",0,"")))
        var list= viewModel.getMainRowViewModel()
        Assert.assertEquals("AceLewis",list?.get(0)?.result?.author)
        Assert.assertEquals("Python",list?.get(0)?.result?.language )

    }

    @Test
    fun `getMainRowViewModel should be null`(){
        var list= viewModel.getMainRowViewModel()
        Assert.assertTrue(list.isNullOrEmpty())
    }
}