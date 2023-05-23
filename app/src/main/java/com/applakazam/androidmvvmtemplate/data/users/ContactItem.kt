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
    var picture: String

    init {
        picture = when (shouldDisplayPicsumPicture()) {
            true -> { Constants.PICSUM_URL }
            false -> {
                val initials = name.split(" ")
                    .filter { it.isNotBlank() }
                    .joinToString { it.first().uppercaseChar().toString() }

                initials.substring(0,1)
            }
        }
    }

    private fun shouldDisplayPicsumPicture() : Boolean {
        return id.isOdd()
    }
}