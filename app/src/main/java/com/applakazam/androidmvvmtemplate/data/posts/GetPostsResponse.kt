package com.applakazam.androidmvvmtemplate.data.posts

/**
 *  Created by paulbisioc on 23.05.2023
 */
sealed class GetPostsResponse() {
    class Success(val postsList: List<PostModel>) : GetPostsResponse()
}