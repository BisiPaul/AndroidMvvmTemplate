package com.applakazam.androidmvvmtemplate.common.utils

/**
 *  Created by paulbisioc on 23.05.2023
 */
object IntExtensions {
    fun Int.isOdd() : Boolean {
        return this % 2 == 1
    }
}