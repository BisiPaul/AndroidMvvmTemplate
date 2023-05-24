package com.applakazam.androidmvvmtemplate.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.databinding.ActivityMainBinding
import com.applakazam.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main

    override val viewModel by viewModels<MainViewModel>()
}