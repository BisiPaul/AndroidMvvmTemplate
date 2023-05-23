package com.applakazam.androidmvvmtemplate.presentation.contacts

import androidx.recyclerview.widget.DiffUtil
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.structure.BaseAdapter
import com.applakazam.androidmvvmtemplate.data.users.ContactItem
import com.applakazam.androidmvvmtemplate.data.users.UserModel
import com.applakazam.androidmvvmtemplate.databinding.ItemContactBinding

/**
 *  Created by paulbisioc on 23.05.2023
 */
class ContactsAdapter(
    private val onItemClicked: () -> Unit
) : BaseAdapter<ContactItem, ItemContactBinding>(
    diffCallback = ContactsAdapterDiffUtil()
) {
    override fun getItemLayout(viewType: Int): Int {
        return R.layout.item_contact
    }

    override fun bind(binding: ItemContactBinding, item: ContactItem, position: Int) {
        binding.item = item
        binding.itemContactContainer.setOnClickListener {
            onItemClicked.invoke()
        }
    }
}

class ContactsAdapterDiffUtil : DiffUtil.ItemCallback<ContactItem>() {

    override fun areItemsTheSame(
        oldItem: ContactItem,
        newItem: ContactItem,
    ): Boolean {
        return oldItem.javaClass == newItem.javaClass
    }

    override fun areContentsTheSame(
        oldItem: ContactItem,
        newItem: ContactItem,
    ): Boolean {
        return oldItem == newItem
    }
}