package com.applakazam.androidmvvmtemplate.contacts.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.applakazam.androidmvvmtemplate.contacts.R
import com.applakazam.androidmvvmtemplate.base.R as RBase
import com.applakazam.androidmvvmtemplate.contacts.databinding.FragmentContactsBinding
import com.applakazam.androidmvvmtemplate.base.view.BaseFragment
import com.applakazam.androidmvvmtemplate.base.common.EventObserver
import com.applakazam.androidmvvmtemplate.extensions.ToastExtensions.shortToast
import com.applakazam.androidmvvmtemplate.navigation.NavDestinationPaths
import com.applakazam.androidmvvmtemplate.navigation.NavExtensions.deeplinkTo
import com.applakazam.androidmvvmtemplate.navigation.NavExtensions.prepareArgsForDeeplink
import com.google.android.material.snackbar.Snackbar
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
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setControls() {

    }

    private fun observe() = with(viewModel) {
        contactsLiveData.observe(viewLifecycleOwner) {
            contactsAdapter.submitList(it)
        }

        navigateToContactDetails.observe(viewLifecycleOwner, EventObserver {
            when(it) {
                null -> { this@ContactsFragment.shortToast("Something went wrong") }
                else -> {
                    val args = it.prepareArgsForDeeplink()
                    findNavController().deeplinkTo(
                        NavDestinationPaths.CONTACT_DETAILS_FRAGMENT,
                        args
                    )
                }
            }
        })

        displayBlockingErrorLiveData.observe(viewLifecycleOwner, EventObserver {
            errorSnackbar = Snackbar.make(binding.root, getString(it), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(RBase.string.retry)) {
                    viewModel.getUsers()
                }

            errorSnackbar?.show()
        })
    }
}