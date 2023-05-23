package com.applakazam.androidmvvmtemplate.presentation.contacts

import androidx.recyclerview.widget.DiffUtil
import com.applakazam.androidmvvmtemplate.R
import com.applakazam.androidmvvmtemplate.common.Constants
import com.applakazam.androidmvvmtemplate.common.structure.BaseAdapter
import com.applakazam.androidmvvmtemplate.common.utils.Extensions.loadLocalBitmap
import com.applakazam.androidmvvmtemplate.common.utils.Extensions.loadUrlImage
import com.applakazam.androidmvvmtemplate.data.users.ContactItem
import com.applakazam.androidmvvmtemplate.databinding.ItemContactBinding

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
        if (item.pictureUrlOrNameInitials == Constants.PICSUM_URL) {
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