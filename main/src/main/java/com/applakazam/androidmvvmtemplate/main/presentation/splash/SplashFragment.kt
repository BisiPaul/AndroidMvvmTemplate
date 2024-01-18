package com.applakazam.androidmvvmtemplate.main.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applakazam.androidmvvmtemplate.main.R
import com.applakazam.androidmvvmtemplate.base.view.BaseFragment
import com.applakazam.androidmvvmtemplate.base.common.EventObserver
import com.applakazam.androidmvvmtemplate.navigation.NavDestinationPaths
import com.applakazam.androidmvvmtemplate.navigation.NavExtensions.deeplinkTo
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
            findNavController().popBackStack(R.id.navigation_splash, true)
            findNavController().deeplinkTo(NavDestinationPaths.HOME_FRAGMENT)
        })
    }
}