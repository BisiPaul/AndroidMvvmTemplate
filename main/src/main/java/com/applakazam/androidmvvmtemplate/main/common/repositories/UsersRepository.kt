package com.applakazam.androidmvvmtemplate.main.common.repositories

import com.applakazam.base.common.Resource
import com.applakazam.base.common.ResponseInterpreter
import com.applakazam.androidmvvmtemplate.main.common.structure.api.ServiceApi
import com.applakazam.androidmvvmtemplate.main.data.posts.GetPostsResponse
import com.applakazam.androidmvvmtemplate.main.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.main.domain.mappers.GetPostsResponseMapper
import com.applakazam.androidmvvmtemplate.main.domain.mappers.GetUsersResponseMapper
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
    private val serviceApi: ServiceApi,
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