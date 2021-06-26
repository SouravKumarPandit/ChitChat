package com.sourav.chitchat.ui.home.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.sourav.chitchat.R
import com.sourav.chitchat.databinding.ViewCardHorizontalBinding

class ChatCardItem : AbstractBindingItem<ViewCardHorizontalBinding>() {
    var name: String? = null

    override val type: Int
        get() = R.id.item_chat_card

    override fun bindView(binding: ViewCardHorizontalBinding, payloads: List<Any>) {
//        binding.name.text = name
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ViewCardHorizontalBinding {
        return ViewCardHorizontalBinding.inflate(inflater, parent, false)
    }
}