package com.applakazam.androidmvvmtemplate.base.common

import androidx.fragment.app.Fragment
import com.applakazam.androidmvvmtemplate.base.common.Functions.createBitmapOfInitials
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.google.android.material.imageview.ShapeableImageView

object Extensions {

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
}