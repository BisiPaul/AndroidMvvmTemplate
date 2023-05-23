package com.applakazam.androidmvvmtemplate.common.repositories

import com.applakazam.androidmvvmtemplate.common.Resource
import com.applakazam.androidmvvmtemplate.common.ResponseInterpreter
import com.applakazam.androidmvvmtemplate.common.structure.api.ServiceApi
import com.applakazam.androidmvvmtemplate.data.users.GetUsersResponse
import com.applakazam.androidmvvmtemplate.domain.mappers.GetUsersResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by paulbisioc on 22.05.2023
 */
interface UsersRepository {
    suspend fun getUsers(): Resource<GetUsersResponse>
}

class UsersRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val responseInterpreter: ResponseInterpreter,
    private val getUsersResponseMapper: GetUsersResponseMapper,
) : UsersRepository {

    override suspend fun getUsers(): Resource<GetUsersResponse> {
        return withContext(Dispatchers.IO) {
            val response = serviceApi.getUsers()

            return@withContext responseInterpreter.interpret(response, getUsersResponseMapper)
        }
    }
}