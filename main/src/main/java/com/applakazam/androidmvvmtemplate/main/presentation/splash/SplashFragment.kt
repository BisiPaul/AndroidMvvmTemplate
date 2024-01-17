package com.applakazam.androidmvvmtemplate.main.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applakazam.androidmvvmtemplate.main.R
import com.applakazam.base.view.BaseFragment
import com.applakazam.base.common.EventObserver
import com.applakazam.androidmvvmtemplate.main.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override val layoutId = R.layout.fragment_splash

    override val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setControls()
        observe()
    }

    private fun setControls() {

    }

    private fun observe() = with(viewModel) {
        navigateToHome.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        })
    }
}