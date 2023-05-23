package com.applakazam.androidmvvmtemplate.data.users

import com.applakazam.androidmvvmtemplate.common.Constants
import com.applakazam.androidmvvmtemplate.common.utils.IntExtensions.isOdd

/**
 *  Created by paulbisioc on 23.05.2023
 */
data class ContactItem(
    val id: Int,
    val name: String
) {
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