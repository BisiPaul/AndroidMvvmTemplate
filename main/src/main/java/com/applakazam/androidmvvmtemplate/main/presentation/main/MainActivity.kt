package com.applakazam.androidmvvmtemplate.main.presentation.main

import androidx.activity.viewModels
import com.applakazam.androidmvvmtemplate.main.R
import com.applakazam.androidmvvmtemplate.main.databinding.ActivityMainBinding
import com.applakazam.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main

    override val viewModel by viewModels<MainViewModel>()
}