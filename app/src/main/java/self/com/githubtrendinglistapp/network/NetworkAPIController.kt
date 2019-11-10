package com.android.rahul.movies.network

import okhttp3.JavaNetCookieJar
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit


object NetworkAPIController {
    private var retrofit: Retrofit? = null

    // Retrofit initializer.
    fun getApiClient(url: String): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        if (retrofit == null) {
            val ok = OkHttpClient.Builder()
                .cookieJar(JavaNetCookieJar(cookieManager))
                .addNetworkInterceptor(interceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create()
                )
                .client(
                    ok.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(
                        30,
                        TimeUnit.SECONDS
                    ).writeTimeout(30, TimeUnit.SECONDS).build()
                )
                .build()
        }
        return retrofit
    }


}