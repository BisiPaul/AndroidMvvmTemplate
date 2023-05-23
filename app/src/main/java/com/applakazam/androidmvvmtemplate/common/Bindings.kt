package com.applakazam.androidmvvmtemplate.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.google.android.material.imageview.ShapeableImageView

/**
 *  Created by paulbisioc on 23.05.2023
 */

@BindingAdapter("android:dataLoading")
fun <T> showDataLoading(view: View, resourceWrapper: LiveData<Resource<T>>) {
    view.visibility = if (resourceWrapper.value?.status == Status.LOADING)
        View.VISIBLE else View.GONE
}

@BindingAdapter("android:loadImage")
fun loadImage(view: ShapeableImageView, urlOrInitials: String) {

    Glide.with(view)
        .load(urlOrInitials)
        .transition(
            DrawableTransitionOptions.withCrossFade(
                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            )
        )
        .into(view)
}