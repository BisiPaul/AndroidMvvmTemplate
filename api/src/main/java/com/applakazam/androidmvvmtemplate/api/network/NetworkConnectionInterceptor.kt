package com.applakazam.androidmvvmtemplate.api.network

import com.applakazam.androidmvvmtemplate.api.error.NetworkException
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  Created by paulbisioc on 17.01.2024
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