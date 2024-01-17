package com.applakazam.androidmvvmtemplate.main.domain.mappers

import com.applakazam.base.common.EntityMapper
import com.applakazam.androidmvvmtemplate.main.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.main.data.posts.GetPostsResponse
import javax.inject.Inject

/**
 *  Created by paulbisioc on 23.05.2023
 */
class GetPostsResponseMapper @Inject constructor() :
    EntityMapper<GetPostsEntity, GetPostsResponse> {
    override fun mapFromEntity(entity: GetPostsEntity): GetPostsResponse {
        return GetPostsResponse.Success(entity.postsList)
    }
}