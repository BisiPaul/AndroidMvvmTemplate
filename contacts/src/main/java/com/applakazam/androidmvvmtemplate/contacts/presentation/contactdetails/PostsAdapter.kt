package com.applakazam.androidmvvmtemplate.contacts.presentation.contactdetails

import androidx.recyclerview.widget.DiffUtil
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.posts.PostModel
import com.applakazam.androidmvvmtemplate.base.adapter.BaseAdapter
import com.applakazam.androidmvvmtemplate.contacts.R
import com.applakazam.androidmvvmtemplate.contacts.databinding.ItemContactPostBinding

/**
 *  Created by paulbisioc on 23.05.2023
 */
class PostsAdapter() : BaseAdapter<PostModel, ItemContactPostBinding>(
    diffCallback = PostsAdapterDiffUtil()
) {
    override fun getItemLayout(viewType: Int): Int {
        return R.layout.item_contact_post
    }

    override fun bind(binding: ItemContactPostBinding, item: PostModel, position: Int) {
        binding.item = item
    }
}

class PostsAdapterDiffUtil : DiffUtil.ItemCallback<PostModel>() {

    override fun areItemsTheSame(
        oldItem: PostModel,
        newItem: PostModel,
    ): Boolean {
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(
        oldItem: PostModel,
        newItem: PostModel,
    ): Boolean {
        return oldItem == newItem
    }
}