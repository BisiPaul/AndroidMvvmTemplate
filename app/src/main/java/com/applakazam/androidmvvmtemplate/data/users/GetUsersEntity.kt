package com.applakazam.androidmvvmtemplate.data.users

import com.squareup.moshi.FromJson

/**
 *  Created by paulbisioc on 22.05.2023
 */
data class GetUsersEntity(
    val usersList: List<UserModel>,
) {
    class GetUsersAdapter {
        @FromJson
        fun fromJson(json: List<UserModel>): GetUsersEntity {
            return GetUsersEntity(json)
        }
    }
}