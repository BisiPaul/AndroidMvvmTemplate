package com.applakazam.base.common.extensions

/**
 *  Created by paulbisioc on 23.05.2023
 */
object IntExtensions {
    fun Int.isOdd() : Boolean {
        return this % 2 == 1
    }
}