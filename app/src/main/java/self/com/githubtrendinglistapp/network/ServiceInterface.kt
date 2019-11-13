package com.android.rahul.movies.network

import io.reactivex.Observable
import retrofit2.http.GET
import self.com.githubtrendinglistapp.datamodel.Repositories
import self.com.githubtrendinglistapp.datamodel.TrendingData

/**
 * Service Interface to fetch data
 *
 */
interface ServiceInterface{
    /**
     * get repository list from the service
     */
    @GET("repositories")
    fun fetchTrendingRepositories(): Observable<List<Repositories>>
}