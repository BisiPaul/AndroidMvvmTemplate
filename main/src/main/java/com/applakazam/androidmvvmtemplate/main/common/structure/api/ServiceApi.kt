package com.applakazam.androidmvvmtemplate.main.common.structure.api

import com.applakazam.androidmvvmtemplate.main.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.main.data.users.GetUsersEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("public/v2/users/")
    suspend fun getUsers(): Response<GetUsersEntity>

    @GET("public/v2/users/{userId}/posts")
    suspend fun getPosts(@Path("userId") userId: Int): Response<GetPostsEntity>
}