package self.com.githubtrendinglistapp.viewmodel

import self.com.githubtrendinglistapp.datamodel.Repositories

// view model for the main movie list
class MainRowViewModel(val result: Repositories?){
    fun getStars():String? {
       return result?.let { it.stars.toString() }
    }
    fun getForks():String? {
        return result?.let { it.forks.toString() }
    }
}