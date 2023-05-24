package com.applakazam.androidmvvmtemplate.common.structure

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _navigateBack = MutableLiveData<Event<Unit>>()
    val navigateBack: LiveData<Event<Unit>>
        get() = _navigateBack

    private val _displayBlockingErrorLiveData = MutableLiveData<Event<@StringRes Int>>()
    val displayBlockingErrorLiveData: LiveData<Event<Int>>
        get() = _displayBlockingErrorLiveData

    protected fun displayRequestError(@StringRes messageRes: Int) {
        _displayBlockingErrorLiveData.value = Event(messageRes)
    }
}