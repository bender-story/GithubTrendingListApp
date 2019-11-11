package self.com.githubtrendinglistapp.network

import com.android.rahul.movies.network.ServiceInterface
import io.reactivex.Observable
import self.com.githubtrendinglistapp.datamodel.Repositories

class MockServiceImpl :ServiceInterface{
    override fun fetchTrendingRepositories(): Observable<Repositories> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}