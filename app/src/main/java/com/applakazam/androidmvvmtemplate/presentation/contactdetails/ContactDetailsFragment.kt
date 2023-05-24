package com.applakazam.androidmvvmtemplate.presentation.contactdetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.base.common.Constants
import com.applakazam.base.view.BaseFragment
import com.applakazam.base.common.EventObserver
import com.applakazam.base.common.extensions.Extensions.loadLocalBitmap
import com.applakazam.base.common.extensions.Extensions.loadUrlImage
import com.applakazam.androidmvvmtemplate.databinding.FragmentContactDetailsBinding
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
            if (it.pictureUrlOrNameInitials == Constants.PICSUM_URL) {
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
                .setAction(getString(R.string.retry)) {
                    viewModel.getPosts()
                }

            errorSnackbar?.show()
        })
    }
}