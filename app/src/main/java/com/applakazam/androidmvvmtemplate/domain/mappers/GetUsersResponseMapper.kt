package com.applakazam.androidmvvmtemplate.domain.mappers

import com.applakazam.base.common.EntityMapper
import com.applakazam.androidmvvmtemplate.data.users.GetUsersEntity
import com.applakazam.androidmvvmtemplate.data.users.GetUsersResponse
import javax.inject.Inject

/**
 *  Created by paulbisioc on 22.05.2023
 */
class GetUsersResponseMapper @Inject constructor() :
    EntityMapper<GetUsersEntity, GetUsersResponse> {
    override fun mapFromEntity(entity: GetUsersEntity): GetUsersResponse {
        return GetUsersResponse.Success(entity.usersList)
    }
}

