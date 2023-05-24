package com.applakazam.androidmvvmtemplate.data.users

import com.applakazam.base.common.Constants
import com.applakazam.base.common.extensions.IntExtensions.isOdd
import java.io.Serializable

/**
 *  Created by paulbisioc on 23.05.2023
 */
data class ContactItem(
    val id: Int,
    val name: String,
    val email: String
) : Serializable {
    var pictureUrlOrNameInitials: String

    init {
        pictureUrlOrNameInitials = when (shouldDisplayPicsumPicture()) {
            true -> { Constants.PICSUM_URL }
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