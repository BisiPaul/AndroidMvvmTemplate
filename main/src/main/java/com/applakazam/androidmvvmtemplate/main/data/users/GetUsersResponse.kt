package com.applakazam.androidmvvmtemplate.main.data.users

sealed class GetUsersResponse(
) {
    class Success(val usersList: List<UserModel>) : GetUsersResponse()
}