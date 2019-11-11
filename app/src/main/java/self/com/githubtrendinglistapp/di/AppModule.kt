package self.com.githubtrendinglistapp.di

import com.android.rahul.movies.network.AppServiceRepo
import org.koin.dsl.module

val appModule= module {
    single { AppServiceRepo(get()) }
}