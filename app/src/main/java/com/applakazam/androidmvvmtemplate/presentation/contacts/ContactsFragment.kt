package com.applakazam.androidmvvmtemplate.presentation.contacts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.structure.BaseFragment
import com.applakazam.androidmvvmtemplate.common.structure.EventObserver
import com.applakazam.androidmvvmtemplate.databinding.FragmentContactsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by paulbisioc on 23.05.2023
 */
@AndroidEntryPoint
class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>() {

    override val layoutId: Int = R.layout.fragment_contacts

    override val viewModel by viewModels<ContactsViewModel>()

    private lateinit var contactsAdapter: ContactsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setControls()
        observe()
    }

    private fun setupRecyclerView() {
        contactsAdapter = ContactsAdapter(
            onItemClicked = viewModel::onContactClicked
        )

        binding.recyclerViewContacts.apply {
            adapter = contactsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setControls() {

    }

    private fun observe() = with(viewModel) {
        contactsLiveData.observe(viewLifecycleOwner, EventObserver {
            contactsAdapter.submitList(it)
        })
    }
}