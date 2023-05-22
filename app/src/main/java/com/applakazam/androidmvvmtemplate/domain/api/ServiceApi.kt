package com.applakazam.androidmvvmtemplate.domain.api

import com.applakazam.androidmvvmtemplate.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.data.users.UserModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("public/v2/users/")
    suspend fun getUsers(): Response<List<UserModel>>
}