package com.applakazam.base.common.network

import com.applakazam.base.error.NetworkException
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  Created by paulbisioc on 23.05.2023
 */
class NetworkConnectionInterceptor(private val networkMonitor: NetworkMonitor) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (networkMonitor.isConnected()) {
            return chain.proceed(request)
        } else {
            throw NetworkException()
        }
    }
}