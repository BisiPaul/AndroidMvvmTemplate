package com.applakazam.androidmvvmtemplate.api.features.contacts.data.users

import com.applakazam.androidmvvmtemplate.api.features.contacts.ContactsApiConstants
import com.applakazam.androidmvvmtemplate.extensions.IntExtensions.isOdd
import com.applakazam.androidmvvmtemplate.api.NavigationArgument
import kotlinx.serialization.Serializable

/**
 *  Created by paulbisioc on 23.05.2023
 */
@Serializable
data class ContactItem(
    val id: Int,
    val name: String,
    val email: String
):  NavigationArgument() {
    var pictureUrlOrNameInitials: String

    init {
        pictureUrlOrNameInitials = when (shouldDisplayPicsumPicture()) {
            true -> { ContactsApiConstants.PICSUM_URL }
            false -> {
                val initials = name.split(" ")
                    .filter { it.isNotBlank() }
                    .joinToString("") { it.first().uppercaseChar().toString() }

                initials.substring(0,2)
            }
        }
    }

    private fun shouldDisplayPicsumPicture() : Boolean {
        return id.isOdd()
    }
}