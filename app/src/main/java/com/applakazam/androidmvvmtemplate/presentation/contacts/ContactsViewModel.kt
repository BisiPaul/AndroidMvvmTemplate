package com.applakazam.androidmvvmtemplate.presentation.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.applakazam.androidmvvmtemplate.common.Constants
import com.applakazam.androidmvvmtemplate.common.Resource
import com.applakazam.androidmvvmtemplate.common.repositories.UsersRepository
import com.applakazam.androidmvvmtemplate.common.structure.BaseViewModel
import com.applakazam.androidmvvmtemplate.common.structure.Event
import com.applakazam.androidmvvmtemplate.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.data.users.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 *  Created by paulbisioc on 23.05.2023
 */
@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseViewModel() {

    private val _requestLiveData = MutableLiveData<Resource<GetUsersResponse>>()
    val requestLiveData: LiveData<Resource<GetUsersResponse>>
        get() = _requestLiveData

    private val _usersLiveData = MutableLiveData<Event<List<UserModel>>>()
    val usersLiveData: LiveData<Event<List<UserModel>>>
        get() = _usersLiveData

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            _requestLiveData.value = Resource.loading()
            val request = usersRepository.getUsers()
            val response = request.data

            if (response == null) {
                _requestLiveData.value =
                    Resource.error(Constants.GENERAL_ERROR_CODE, Exception())
                return@launch
            }

            when (response) {
                is GetUsersResponse.Success -> {
                    val activeUsers = response.usersList.filter { it.status == "active" }
                    _usersLiveData.value = Event(activeUsers)
                }

                else -> {
                    // TODO @Paul: show something went wrong error
                }
            }

            _requestLiveData.postValue(Resource.success(response))
        }
    }
}