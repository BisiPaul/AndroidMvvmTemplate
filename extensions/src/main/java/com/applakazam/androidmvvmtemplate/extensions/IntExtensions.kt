package com.applakazam.androidmvvmtemplate.extensions

/**
 *  Created by paulbisioc on 17.01.2024
 */
object IntExtensions {
    fun Int.isOdd() : Boolean {
        return this % 2 == 1
    }
}