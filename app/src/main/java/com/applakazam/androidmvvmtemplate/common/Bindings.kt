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

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}