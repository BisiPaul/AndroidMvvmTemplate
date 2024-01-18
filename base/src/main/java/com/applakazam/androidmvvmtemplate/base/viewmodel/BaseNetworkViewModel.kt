package com.applakazam.androidmvvmtemplate.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applakazam.androidmvvmtemplate.base.common.Event
import com.applakazam.androidmvvmtemplate.api.error.BaseErrorTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

/**
 *  Created by paulbisioc on 24.05.2023
 */
@HiltViewModel
open class BaseNetworkViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var errorTranslator: BaseErrorTranslator

    private val _displayBlockingErrorLiveData = MutableLiveData<Event<@StringRes Int>>()
    val displayBlockingErrorLiveData: LiveData<Event<Int>>
        get() = _displayBlockingErrorLiveData

    fun handleError(exception: Exception?) {
        displayRequestError(translateError(exception))
    }

    @StringRes
    private fun translateError(exception: Exception?) : Int {
        return errorTranslator.translateError(exception)
    }

    private fun displayRequestError(@StringRes messageRes: Int) {
        _displayBlockingErrorLiveData.value = Event(messageRes)
    }
}