package self.com.githubtrendinglistapp.viewmodel

import self.com.githubtrendinglistapp.datamodel.Repositories

/**
 * View model for the row item of the list which loads data through databinding
 */
class MainRowViewModel(val result: Repositories?){
    /**
     * get the star param and convert it to string
     */
    fun getStars():String? {
       return result?.let { it.stars.toString() }
    }
    /**
     * get the forks param and convert it to string
     */
    fun getForks():String? {
        return result?.let { it.forks.toString() }
    }
}