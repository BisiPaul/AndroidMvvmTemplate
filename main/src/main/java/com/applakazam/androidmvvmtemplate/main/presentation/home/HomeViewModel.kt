package com.applakazam.androidmvvmtemplate.main.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applakazam.base.viewmodel.BaseViewModel
import com.applakazam.base.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    private var _navigateToContacts = MutableLiveData<Event<Unit>>()
    val navigateToContacts: LiveData<Event<Unit>>
        get() = _navigateToContacts

    fun navigateToContacts() {
        _navigateToContacts.value = Event(Unit)
    }
}