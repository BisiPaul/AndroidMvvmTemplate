package com.applakazam.base.error

import androidx.annotation.StringRes
import com.applakazam.androidmvvmtemplate.base.R
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 *  Created by paulbisioc on 23.05.2023
 */
interface ErrorTranslator {
    @StringRes
    fun translateError(exception: java.lang.Exception?): Int
}

open class BaseErrorTranslator() : ErrorTranslator {

    @StringRes
    override fun translateError(exception: Exception?): Int {
        return when (exception) {
            is ResponseTimeoutException, is TimeoutException, is SocketTimeoutException, is UnknownHostException ->
                R.string.base_error_timeout

            is HttpException -> R.string.base_error_general_message
            is SocketException, is NetworkException -> R.string.base_error_no_internet
            else -> R.string.base_error_general_message
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