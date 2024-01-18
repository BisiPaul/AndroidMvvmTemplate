package com.applakazam.androidmvvmtemplate.extensions

import android.view.View

/**
 *  Created by paulbisioc on 18.01.2024
 */
object ViewExtensions {

    // View visibility
    fun View?.visible() {
        this?.visibility = View.VISIBLE
    }

    fun View?.invisible() {
        this?.visibility = View.INVISIBLE
    }

    fun View?.gone() {
        this?.visibility = View.GONE
    }
}