package com.applakazam.androidmvvmtemplate.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

/**
 *  Created by paulbisioc on 23.05.2023
 */

@BindingAdapter("android:dataLoading")
fun <T> showDataLoading(view: View, resourceWrapper: LiveData<Resource<T>>) {
    view.visibility = if (resourceWrapper.value?.status == Status.LOADING)
        View.VISIBLE else View.GONE
}