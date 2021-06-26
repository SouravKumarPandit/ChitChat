package com.sourav.chitchat.ui.home.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import com.sourav.chitchat.R
import com.sourav.chitchat.databinding.ViewItemVerticalBinding

class ChatDetailsItem(value:Int) : ModelAbstractBindingItem<Int,ViewItemVerticalBinding>(value) {
    var name: String? = null

    override val type: Int
        get() = R.id.item_chat_details

    override fun bindView(binding: ViewItemVerticalBinding, payloads: List<Any>) {
//        binding.name.text = name
        binding.imageView.setPendingMessage(model)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ViewItemVerticalBinding {
        return ViewItemVerticalBinding.inflate(inflater, parent, false)
    }
}