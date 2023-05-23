package com.applakazam.androidmvvmtemplate.common.structure.api

import com.applakazam.androidmvvmtemplate.data.users.GetUsersEntity
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("public/v2/users/")
    suspend fun getUsers(): Response<GetUsersEntity>
}