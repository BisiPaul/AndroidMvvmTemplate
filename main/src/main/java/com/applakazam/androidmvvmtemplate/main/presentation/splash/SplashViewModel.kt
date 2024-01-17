package com.applakazam.androidmvvmtemplate.main.presentation.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applakazam.base.viewmodel.BaseViewModel
import com.applakazam.base.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private var _navigateToHome = MutableLiveData<Event<Unit>>()
    val navigateToHome: LiveData<Event<Unit>>
        get() = _navigateToHome

    init {
        // TODO @Paul: do what you need before entering the app
        _navigateToHome.value = Event(Unit)
    }
}