package com.applakazam.androidmvvmtemplate.data.users

sealed class GetUsersResponse(
) {
    class Success(val usersList: List<UserModel>) : GetUsersResponse()
}