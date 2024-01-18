package com.applakazam.androidmvvmtemplate.api.features.contacts.data.users

sealed class GetUsersResponse(
) {
    class Success(val usersList: List<UserModel>) : GetUsersResponse()
}