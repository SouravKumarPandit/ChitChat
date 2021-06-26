package com.sourav.chitchat.ui.details.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import com.sourav.chitchat.R
import com.sourav.chitchat.databinding.ItemImageSendBinding
import com.sourav.chitchat.databinding.ItemJoinedInfoBinding

class ChatJoinedInfoItemBinding(item: String) :
    ModelAbstractBindingItem<String, ItemJoinedInfoBinding>(item) {
    override val type: Int = R.id.chat_joined_item_id
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemJoinedInfoBinding {
        return ItemJoinedInfoBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemJoinedInfoBinding, payloads: List<Any>) {
        binding.mar1334.text=model

        /* val layoutParams = binding.userImage.layoutParams as ConstraintLayout.LayoutParams
         if (model % 2 == 0) {
             layoutParams.setMargins(0, 15.px, 0, 0)
         } else {
             layoutParams.setMargins(0, 0, 0, 15.px)

         }
 */
//        super.bindView(binding, payloads)
    }


}