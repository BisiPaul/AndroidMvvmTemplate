package com.applakazam.androidmvvmtemplate.presentation.contactdetails

import androidx.recyclerview.widget.DiffUtil
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.structure.BaseAdapter
import com.applakazam.androidmvvmtemplate.data.posts.PostModel
import com.applakazam.androidmvvmtemplate.databinding.ItemContactPostBinding

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