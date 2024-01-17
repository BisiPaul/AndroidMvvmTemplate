package com.applakazam.androidmvvmtemplate.main.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applakazam.androidmvvmtemplate.main.R
import com.applakazam.base.view.BaseFragment
import com.applakazam.base.common.EventObserver
import com.applakazam.androidmvvmtemplate.main.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val layoutId: Int = R.layout.fragment_home

    override val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setControls()
        observe()
    }

    private fun setControls() {
        binding.goToContactsButton.setOnClickListener {
            viewModel.navigateToContacts()
        }
    }

    private fun observe() = with(viewModel) {
       navigateToContacts.observe(viewLifecycleOwner, EventObserver {
           findNavController().navigate(R.id.action_homeFragment_to_contactsFragment)
       })
    }
}