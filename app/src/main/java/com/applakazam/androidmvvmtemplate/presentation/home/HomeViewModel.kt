package com.applakazam.androidmvvmtemplate.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.applakazam.androidmvvmtemplate.common.structure.BaseViewModel
import com.applakazam.androidmvvmtemplate.domain.api.ServiceApi
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    // TODO @Paul: move the call in a repository
    private val serviceApi: ServiceApi
) : BaseViewModel() {

    init {

    }
    suspend fun getUsers() {
        serviceApi.getUsers().let {
            when (it.isSuccessful) {
                true -> {
                }
                false -> {
                }
            }
        }
    }
}