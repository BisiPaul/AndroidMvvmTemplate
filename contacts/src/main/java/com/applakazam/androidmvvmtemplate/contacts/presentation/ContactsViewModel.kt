package com.applakazam.androidmvvmtemplate.contacts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.applakazam.androidmvvmtemplate.api.ApiConstants.GENERAL_ERROR_CODE
import com.applakazam.androidmvvmtemplate.api.Resource
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.ContactItem
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.api.features.contacts.repositories.UsersRepository
import com.applakazam.androidmvvmtemplate.api.error.GeneralException
import com.applakazam.androidmvvmtemplate.base.common.Event
import com.applakazam.androidmvvmtemplate.api.error.isTranslatable
import com.applakazam.androidmvvmtemplate.base.viewmodel.BaseNetworkViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 *  Created by paulbisioc on 23.05.2023
 */
@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseNetworkViewModel() {

    private val _requestLiveData = MutableLiveData<Resource<GetUsersResponse>>()
    val requestLiveData: LiveData<Resource<GetUsersResponse>>
        get() = _requestLiveData

    private val _contactsLiveData = MutableLiveData<List<ContactItem>>()
    val contactsLiveData: LiveData<List<ContactItem>>
        get() = _contactsLiveData

    private var _navigateToContactDetails = MutableLiveData<Event<ContactItem?>>()
    val navigateToContactDetails: LiveData<Event<ContactItem?>>
        get() = _navigateToContactDetails

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val exception =
            if (throwable.isTranslatable()) throwable as Exception else GeneralException()
        _requestLiveData.value = Resource.error(GENERAL_ERROR_CODE, exception)

        handleError(exception)
    }

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _requestLiveData.value = Resource.loading()
            val request = usersRepository.getUsers()
            val response = request.data

            if (response == null) {
                _requestLiveData.value =
                    Resource.error(GENERAL_ERROR_CODE, Exception())
                return@launch
            }

            when (response) {
                is GetUsersResponse.Success -> {
                    val activeUsers = response.usersList.filter { it.status == "active" }
                    val contactItemsList: MutableList<ContactItem> = mutableListOf()
                    activeUsers.forEach {
                        contactItemsList.add(
                            ContactItem(
                                it.id,
                                it.name,
                                it.email
                            )
                        )
                    }
                    _contactsLiveData.value = contactItemsList
                }
            }

            _requestLiveData.postValue(Resource.success(response))
        }
    }

    fun onContactClicked(id: Int) {
        _navigateToContactDetails.value = Event(contactsLiveData.value?.firstOrNull { it.id == id})
    }
}