package com.applakazam.androidmvvmtemplate.main.data.posts

/**
 *  Created by paulbisioc on 23.05.2023
 */
data class PostModel(
    val id: Int,
    val user_id: Int,
    val title: String,
    val body: String
)