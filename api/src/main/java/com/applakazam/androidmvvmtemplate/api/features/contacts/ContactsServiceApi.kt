package com.applakazam.androidmvvmtemplate.api.features.contacts

import com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.GetUsersEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactsServiceApi {
    @GET("public/v2/users/")
    suspend fun getUsers(): Response<GetUsersEntity>

    @GET("public/v2/users/{userId}/posts")
    suspend fun getPosts(@Path("userId") userId: Int): Response<GetPostsEntity>
}