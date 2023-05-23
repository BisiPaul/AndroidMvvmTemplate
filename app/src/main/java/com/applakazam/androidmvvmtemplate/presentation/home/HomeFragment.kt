package com.applakazam.androidmvvmtemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.structure.BaseFragment
import com.applakazam.androidmvvmtemplate.common.structure.EventObserver
import com.applakazam.androidmvvmtemplate.databinding.FragmentHomeBinding
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