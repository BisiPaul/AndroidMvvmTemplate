package com.applakazam.androidmvvmtemplate.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.structure.BaseFragment
import com.applakazam.androidmvvmtemplate.common.structure.EventObserver
import com.applakazam.androidmvvmtemplate.databinding.FragmentSplashBinding
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