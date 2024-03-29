package com.applakazam.androidmvvmtemplate.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applakazam.androidmvvmtemplate.base.common.Event

abstract class BaseViewModel : ViewModel() {

    private val _navigateBack = MutableLiveData<Event<Unit>>()
    val navigateBack: LiveData<Event<Unit>>
        get() = _navigateBack
}