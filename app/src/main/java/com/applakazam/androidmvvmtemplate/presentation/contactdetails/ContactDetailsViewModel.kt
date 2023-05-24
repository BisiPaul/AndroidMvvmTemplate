package com.applakazam.androidmvvmtemplate.presentation.contactdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.applakazam.base.common.Constants
import com.applakazam.base.common.Resource
import com.applakazam.base.error.GeneralException
import com.applakazam.androidmvvmtemplate.common.repositories.UsersRepository
import com.applakazam.base.error.isTranslatable
import com.applakazam.base.viewmodel.BaseNetworkViewModel
import com.applakazam.androidmvvmtemplate.data.posts.GetPostsResponse
import com.applakazam.androidmvvmtemplate.data.posts.PostModel
import com.applakazam.androidmvvmtemplate.data.users.ContactItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

/**
 *  Created by paulbisioc on 23.05.2023
 */
@HiltViewModel
class ContactDetailsViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseNetworkViewModel() {
    private val _requestLiveData = MutableLiveData<Resource<GetPostsResponse>>()
    val requestLiveData: LiveData<Resource<GetPostsResponse>>
        get() = _requestLiveData

    private val _contactLiveData = MutableLiveData<ContactItem>()
    val contactLiveData: LiveData<ContactItem>
        get() = _contactLiveData

    private val _postsLiveData = MutableLiveData<List<PostModel>>()
    val postsLiveData: LiveData<List<PostModel>>
        get() = _postsLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val exception =
            if (throwable.isTranslatable()) throwable as Exception else GeneralException()
        _requestLiveData.value = Resource.error(Constants.GENERAL_ERROR_CODE, exception)

        handleError(exception)
    }

    fun handleArgs(args: ContactDetailsFragmentArgs) {
        _contactLiveData.value = args.contactItem
    }

    fun getPosts() {
        viewModelScope.launch(coroutineExceptionHandler) {
            contactLiveData.value?.id?.let { userId ->
                _requestLiveData.value = Resource.loading()
                val request = usersRepository.getPosts(userId)
                val response = request.data

                if (response == null) {
                    _requestLiveData.value =
                        Resource.error(Constants.GENERAL_ERROR_CODE, Exception())
                    return@launch
                }

                when (response) {
                    is GetPostsResponse.Success -> {
                        _postsLiveData.value = response.postsList
                    }
                }

                _requestLiveData.postValue(Resource.success(response))
            }
        }
    }
}