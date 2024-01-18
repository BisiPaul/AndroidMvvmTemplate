package com.applakazam.androidmvvmtemplate.base.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.applakazam.androidmvvmtemplate.api.Resource
import com.applakazam.androidmvvmtemplate.api.Status

/**
 *  Created by paulbisioc on 23.05.2023
 */

@BindingAdapter("dataLoading")
fun <T> showDataLoading(view: View, resourceWrapper: LiveData<Resource<T>>) {
    view.visibility = if (resourceWrapper.value?.status == Status.LOADING)
        View.VISIBLE else View.GONE
}

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}