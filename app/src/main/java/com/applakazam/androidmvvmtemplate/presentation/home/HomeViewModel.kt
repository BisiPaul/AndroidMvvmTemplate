package com.applakazam.androidmvvmtemplate.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applakazam.androidmvvmtemplate.common.structure.BaseViewModel
import com.applakazam.androidmvvmtemplate.common.structure.Event
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