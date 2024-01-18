package com.applakazam.androidmvvmtemplate.api.features.contacts.repositories

import com.applakazam.androidmvvmtemplate.api.Resource
import com.applakazam.androidmvvmtemplate.api.ResponseInterpreter
import com.applakazam.androidmvvmtemplate.api.features.contacts.ContactsServiceApi
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts.GetPostsResponse
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.api.features.contacts.mappers.GetPostsResponseMapper
import com.applakazam.androidmvvmtemplate.api.features.contacts.mappers.GetUsersResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by paulbisioc on 22.05.2023
 */
interface UsersRepository {
    suspend fun getUsers(): Resource<GetUsersResponse>
    suspend fun getPosts(userId: Int): Resource<GetPostsResponse>
}

class UsersRepositoryImpl @Inject constructor(
    private val serviceApi: ContactsServiceApi,
    private val responseInterpreter: ResponseInterpreter,
    private val getUsersResponseMapper: GetUsersResponseMapper,
    private val getPostsResponseMapper: GetPostsResponseMapper,
    ) : UsersRepository {

    override suspend fun getUsers(): Resource<GetUsersResponse> {
        return withContext(Dispatchers.IO) {
            val response = serviceApi.getUsers()

            return@withContext responseInterpreter.interpret(response, getUsersResponseMapper)
        }
    }

    override suspend fun getPosts(userId: Int): Resource<GetPostsResponse> {
        return withContext(Dispatchers.IO) {
            val response = serviceApi.getPosts(userId)

            return@withContext responseInterpreter.interpret(response, getPostsResponseMapper)
        }
    }
}