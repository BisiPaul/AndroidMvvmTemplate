package com.applakazam.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.applakazam.androidmvvmtemplate.base.BR
import com.applakazam.base.viewmodel.BaseViewModel

/**
 *  Created by paulbisioc on 24.05.2023
 */
abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    abstract val viewModel: VM
    private lateinit var _binding: B

    val binding: B
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, layoutId)
        _binding.setVariable(BR.viewModel, viewModel)
        _binding.lifecycleOwner = this
        _binding.executePendingBindings()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.primaryNavigationFragment?.childFragmentManager?.primaryNavigationFragment?.let { fragment ->
            if (fragment is BaseFragment<*, *> && !fragment.allowBackButton()) {
                return
            }
        }
        super.onBackPressed()
    }
}