package com.applakazam.androidmvvmtemplate.api.features.contacts.mappers

import com.applakazam.androidmvvmtemplate.api.EntityMapper
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts.GetPostsEntity
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts.GetPostsResponse
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