package com.applakazam.androidmvvmtemplate.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 *  Created by paulbisioc on 23.05.2023
 */
interface NetworkMonitor {
    fun isConnected(): Boolean
}

class NetworkMonitorImpl(context: Context) : NetworkMonitor {
    private val applicationContext: Context = context.applicationContext

    override fun isConnected(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        val result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return result
    }
}
