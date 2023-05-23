package com.applakazam.androidmvvmtemplate.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.applakazam.androidmvvmtemplate.common.Constants.GENERAL_ERROR_CODE
import com.applakazam.androidmvvmtemplate.common.Resource
import com.applakazam.androidmvvmtemplate.common.repositories.UsersRepository
import com.applakazam.androidmvvmtemplate.common.structure.BaseViewModel
import com.applakazam.androidmvvmtemplate.common.structure.Event
import com.applakazam.androidmvvmtemplate.common.structure.api.ServiceApi
import com.applakazam.androidmvvmtemplate.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.data.users.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
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