package com.sourav.chitchat.ui.details.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import com.sourav.chitchat.R
import com.sourav.chitchat.databinding.ItemChatReciveBinding
import com.sourav.chitchat.databinding.ItemDateInfoBinding
import com.sourav.chitchat.databinding.ViewUserOddShiftBinding
import com.sourav.chitchat.utils.px

class ChatDateItemBinding(item: String) :
    ModelAbstractBindingItem<String, ItemDateInfoBinding>(item) {
    override val type: Int = R.id.chat_date_item_id
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemDateInfoBinding {
        return ItemDateInfoBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemDateInfoBinding, payloads: List<Any>) {
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
