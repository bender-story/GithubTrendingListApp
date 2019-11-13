package com.android.rahul.movies.utils

import android.content.Context
import android.net.ConnectivityManager
/**
 * Network Utils class
 *
 */
object NetworkUtils {
    /**
     * check if network is available else return false
     * @param context application context
     * @return network available true else false
     */
    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}