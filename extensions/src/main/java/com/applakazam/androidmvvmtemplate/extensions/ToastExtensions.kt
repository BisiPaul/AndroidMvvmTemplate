package com.applakazam.androidmvvmtemplate.extensions

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 *  Created by paulbisioc on 18.01.2024
 */
object ToastExtensions {

    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.shortToast(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun Context.longToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}