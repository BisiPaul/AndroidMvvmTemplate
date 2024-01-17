package com.applakazam.androidmvvmtemplate.main.data.posts

import com.squareup.moshi.FromJson

/**
 *  Created by paulbisioc on 23.05.2023
 */
data class GetPostsEntity(
    val postsList: List<PostModel>
) {
    class GetPostsAdapter {
        @FromJson
        fun fromJson(json: List<PostModel>): GetPostsEntity {
            return GetPostsEntity(json)
        }
    }
}