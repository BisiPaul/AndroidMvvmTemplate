package com.applakazam.androidmvvmtemplate.contacts.presentation

import androidx.recyclerview.widget.DiffUtil
import com.applakazam.androidmvvmtemplate.api.features.contacts.ContactsApiConstants
import com.applakazam.androidmvvmtemplate.api.features.contacts.data.users.ContactItem
import com.applakazam.androidmvvmtemplate.contacts.R
import com.applakazam.androidmvvmtemplate.base.adapter.BaseAdapter
import com.applakazam.androidmvvmtemplate.base.common.Extensions.loadLocalBitmap
import com.applakazam.androidmvvmtemplate.base.common.Extensions.loadUrlImage
import com.applakazam.androidmvvmtemplate.contacts.databinding.ItemContactBinding

/**
 *  Created by paulbisioc on 23.05.2023
 */
class ContactsAdapter(
    private val onItemClicked: (id: Int) -> Unit
) : BaseAdapter<ContactItem, ItemContactBinding>(
    diffCallback = ContactsAdapterDiffUtil()
) {
    override fun getItemLayout(viewType: Int): Int {
        return R.layout.item_contact
    }

    override fun bind(binding: ItemContactBinding, item: ContactItem, position: Int) {
        binding.item = item
        binding.itemContactContainer.setOnClickListener {
            onItemClicked.invoke(item.id)
        }
        if (item.pictureUrlOrNameInitials == ContactsApiConstants.PICSUM_URL) {
            binding.itemContactPicture.loadUrlImage(item.pictureUrlOrNameInitials)
        } else {
            binding.itemContactPicture.loadLocalBitmap(item.pictureUrlOrNameInitials)
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