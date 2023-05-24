package com.applakazam.base.common.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.applakazam.base.common.AutoClearedValue
import com.applakazam.base.common.Functions.createBitmapOfInitials
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.google.android.material.imageview.ShapeableImageView

object Extensions {
    // Toast
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.shortToast(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun Context.longToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Creates an [AutoClearedValue] associated with this fragment.
     */
    fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)


    // ShapeableImageView
    fun ShapeableImageView.loadUrlImage(url: String) {
        Glide.with(this)
            .load(url)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into(this)
    }

    fun ShapeableImageView.loadLocalBitmap(initials: String) {
        val bitmap = createBitmapOfInitials(initials)

        Glide.with(this)
            .load(bitmap)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                )
            )
            .into(this)
    }

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