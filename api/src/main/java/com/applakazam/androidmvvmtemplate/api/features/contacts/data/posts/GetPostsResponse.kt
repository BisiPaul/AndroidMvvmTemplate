package com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts

/**
 *  Created by paulbisioc on 23.05.2023
 */
sealed class GetPostsResponse() {
    class Success(val postsList: List<PostModel>) : GetPostsResponse()
}