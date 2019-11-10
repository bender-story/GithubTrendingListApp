package com.android.rahul.movies.network

import io.reactivex.Observable
import retrofit2.http.GET
import self.com.githubtrendinglistapp.datamodel.Repositories


interface ServiceInterface{
    @GET("repositories")
    fun fetchTrendingRepositories(): Observable<Repositories>
}