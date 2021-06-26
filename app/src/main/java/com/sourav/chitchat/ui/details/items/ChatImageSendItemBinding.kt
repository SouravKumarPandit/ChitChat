package com.sourav.chitchat.ui.details.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import com.sourav.chitchat.R
import com.sourav.chitchat.databinding.ItemImageSendBinding

class ChatImageSendItemBinding(item: String) :
    ModelAbstractBindingItem<String, ItemImageSendBinding>(item) {
    override val type: Int = R.id.chat_image_send_item_id
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemImageSendBinding {
        return ItemImageSendBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemImageSendBinding, payloads: List<Any>) {
//        binding.mar1334.text=model

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