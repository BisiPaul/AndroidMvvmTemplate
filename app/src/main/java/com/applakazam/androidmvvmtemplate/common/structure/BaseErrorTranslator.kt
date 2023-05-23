package com.applakazam.androidmvvmtemplate.common.structure

import android.app.Application
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.error.NetworkException
import com.applakazam.androidmvvmtemplate.common.error.ResponseTimeoutException
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 *  Created by paulbisioc on 23.05.2023
 */
interface ErrorTranslator {
    fun translateError(errorCode : Int?, exception: java.lang.Exception?): String
}

open class BaseErrorTranslator(protected val app: Application) : ErrorTranslator {

    override fun translateError(errorCode: Int?, exception: Exception?): String {
        return if (exception != null) {
            translateError(exception)
        } else {
            app.getString(R.string.base_error_general_message)
        }
    }

    protected open fun translateError(ex: Throwable): String {
        return when (ex) {
            is ResponseTimeoutException, is TimeoutException, is SocketTimeoutException, is UnknownHostException -> app.getString(
                R.string.base_error_timeout
            )
            is HttpException -> app.getString(R.string.base_error_general_message)
            is SocketException, is NetworkException ->
                app.getString(R.string.base_error_no_internet)
            else -> app.getString(R.string.base_error_general_message)
        }
    }
}

fun Throwable.isTranslatable(): Boolean {
    return when (this) {
        is ResponseTimeoutException,
        is TimeoutException,
        is SocketTimeoutException,
        is UnknownHostException,
        is HttpException,
        is SocketException,
        is NetworkException -> true
        else -> false
    }
}