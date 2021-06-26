package com.sourav.chitchat.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikepenz.fastadapter.IAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.rubensousa.decorator.LinearMarginDecoration
import com.sourav.chitchat.utils.px

class UserProfileList : RecyclerView {

    //save our FastAdapter
    private var fastItemBindingAdapter: FastItemAdapter<UserItemBinding> = FastItemAdapter()

    init {


    }


    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        //get our recyclerView and do basic setup
        this.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        this.overScrollMode = OVER_SCROLL_NEVER
        this.adapter = fastItemBindingAdapter
        this.clipToPadding = false
        this.clipChildren = false
        this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        this.addItemDecoration(
            LinearMarginDecoration(
                leftMargin = 16.px,
                topMargin = 10.px,
                rightMargin = 16.px,
                bottomMargin = 10.px,
                orientation = HORIZONTAL
            )
        )
        val items = ArrayList<Int>()
        for (i in 0 until 15) {
            items.add(i)
        }
        setData(
            items
        )
    }

    fun clearData() {
        fastItemBindingAdapter.clear()
        this.visibility = View.GONE
    }

    fun setData(
        homeList: List<Int>
    ) {
        if (homeList.isNullOrEmpty()) {
            this.visibility = View.GONE
            return
        }
        this.visibility = View.VISIBLE
        val items = ArrayList<UserItemBinding>()
        homeList.forEach { homeItem ->
            homeItem.let { item ->
                items.add(
                    UserItemBinding(item)
                )
            }
        }

        fastItemBindingAdapter.add(items)

    }
}
