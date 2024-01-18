package com.applakazam.androidmvvmtemplate.contacts.presentation.contactdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.applakazam.androidmvvmtemplate.api.features.contacts.ContactsApiConstants
import com.applakazam.androidmvvmtemplate.contacts.R
import com.applakazam.androidmvvmtemplate.base.R as RBase
import com.applakazam.androidmvvmtemplate.contacts.databinding.FragmentContactDetailsBinding
import com.applakazam.androidmvvmtemplate.base.view.BaseFragment
import com.applakazam.androidmvvmtemplate.base.common.EventObserver
import com.applakazam.androidmvvmtemplate.base.common.Extensions.loadLocalBitmap
import com.applakazam.androidmvvmtemplate.base.common.Extensions.loadUrlImage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by paulbisioc on 23.05.2023
 */
@AndroidEntryPoint
class ContactDetailsFragment() : BaseFragment<ContactDetailsViewModel, FragmentContactDetailsBinding>() {

    override val layoutId: Int = R.layout.fragment_contact_details

    override val viewModel by viewModels<ContactDetailsViewModel>()

    private val args: ContactDetailsFragmentArgs by navArgs()

    private lateinit var postsAdapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.handleArgs(args)
        setupRecyclerView()
        setControls()
        observe()

        viewModel.getPosts()
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter()

        binding.recyclerViewPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setControls() {
        binding.backContactDetails.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observe() = with(viewModel) {
        contactLiveData.observe(viewLifecycleOwner) {
            if (it.pictureUrlOrNameInitials == ContactsApiConstants.PICSUM_URL) {
                binding.pictureContactDetails.loadUrlImage(it.pictureUrlOrNameInitials)
            } else {
                binding.pictureContactDetails.loadLocalBitmap(it.pictureUrlOrNameInitials)
            }
        }

        postsLiveData.observe(viewLifecycleOwner) {
            postsAdapter.submitList(it)
        }

        displayBlockingErrorLiveData.observe(viewLifecycleOwner, EventObserver {
            errorSnackbar = Snackbar.make(binding.root, getString(it), Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(RBase.string.retry)) {
                    viewModel.getPosts()
                }

            errorSnackbar?.show()
        })
    }
}