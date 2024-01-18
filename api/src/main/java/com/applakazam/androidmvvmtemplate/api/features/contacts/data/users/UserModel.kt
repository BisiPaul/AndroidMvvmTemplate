package com.applakazam.androidmvvmtemplate.api.features.contacts.data.users

data class UserModel(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
)