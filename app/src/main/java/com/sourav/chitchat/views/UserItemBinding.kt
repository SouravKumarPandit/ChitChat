package com.sourav.chitchat.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.mikepenz.fastadapter.binding.ModelAbstractBindingItem
import com.sourav.chitchat.R
import com.sourav.chitchat.databinding.ViewUserOddShiftBinding
import com.sourav.chitchat.utils.px

class UserItemBinding(item: Int) :
    ModelAbstractBindingItem<Int, ViewUserOddShiftBinding>(item) {
    override val type: Int = R.id.user_item_id
    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ViewUserOddShiftBinding {
        return ViewUserOddShiftBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ViewUserOddShiftBinding, payloads: List<Any>) {

        val layoutParams = binding.userImage.layoutParams as ConstraintLayout.LayoutParams
        if (model % 2 == 0) {
            layoutParams.setMargins(0, 15.px, 0, 0)
        } else {
            layoutParams.setMargins(0, 0, 0, 15.px)

        }

//        super.bindView(binding, payloads)
    }

    override fun unbindView(binding: ViewUserOddShiftBinding) {
//        binding.imageUrl = null
    }

}
