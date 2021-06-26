package com.sourav.chitchat.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.laalsa.riderhive.ui.screen.login.ChatHomeViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.rubensousa.decorator.LinearMarginDecoration
import com.sourav.chitchat.R
import com.sourav.chitchat.base.BaseFragment
import com.sourav.chitchat.databinding.FragmentChatHomeBinding
import com.sourav.chitchat.ui.home.data.ChatHomeRemoteDataSource
import com.sourav.chitchat.ui.home.data.ChatHomeRepository
import com.sourav.chitchat.ui.home.items.ChatCardItem
import com.sourav.chitchat.ui.home.items.ChatDetailsItem
import com.sourav.chitchat.utils.px


class ChatHomeFragment : BaseFragment<FragmentChatHomeBinding, ChatHomeViewModel>(), IChatHome {
    override val fvm: ChatHomeViewModel =
        ChatHomeViewModel(ChatHomeRepository(ChatHomeRemoteDataSource(object :
            ChatHomeServiceAPI {})))


    override fun getLayoutId() = R.layout.fragment_chat_home


    private val navController by lazy {
        findNavController()
    }

    override fun onFragmentReady(view: View) {
//        bd.loginViewmodel = fvm

        /* bd.button.setOnClickListener {
             navController.navigate(ChatHomeFragmentDirections.actionNavChatHomeToNavChatDetails())
         }*/

        //create the ItemAdapter holding your Items
        val cardItem = ItemAdapter<ChatCardItem>()
//create the managing FastAdapter, by passing in the itemAdapter
        val fastCardAdapter = FastAdapter.with(cardItem)
        bd.recyclerHorizontal.adapter = fastCardAdapter
        //create the ItemAdapter holding your Items
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())
        cardItem.add(ChatCardItem())

        fastCardAdapter.onClickListener = { view, adapter, item, position ->
            navigateToChatDetails()
            false
        }

        bd.recyclerHorizontal.addItemDecoration(
            LinearMarginDecoration(
                leftMargin = 16.px,
                topMargin = 16.px,
                rightMargin = 16.px,
                bottomMargin = 25.px,
                orientation = RecyclerView.HORIZONTAL
            )
        )


        val itemAdapter = ItemAdapter<ChatDetailsItem>()
//create the managing FastAdapter, by passing in the itemAdapter
        val fastAdapter = FastAdapter.with(itemAdapter)
        bd.recyclerVertical.adapter = fastAdapter

        itemAdapter.add(ChatDetailsItem(5))
        itemAdapter.add(ChatDetailsItem(0))
        itemAdapter.add(ChatDetailsItem(22))
        itemAdapter.add(ChatDetailsItem(13))
        itemAdapter.add(ChatDetailsItem(0))
        itemAdapter.add(ChatDetailsItem(1))
        itemAdapter.add(ChatDetailsItem(6))
        itemAdapter.add(ChatDetailsItem(0))
        itemAdapter.add(ChatDetailsItem(2))
        itemAdapter.add(ChatDetailsItem(9))



        bd.recyclerVertical.addItemDecoration(
            LinearMarginDecoration(
                leftMargin = 16.px,
                topMargin = 16.px,
                rightMargin = 16.px,
                bottomMargin = 16.px,
                orientation = RecyclerView.VERTICAL
            )
        )
        fastAdapter.onClickListener = { view, adapter, item, position ->
            navigateToChatDetails()
            false
        }


    }

    private fun navigateToChatDetails() {
        navController.navigate(ChatHomeFragmentDirections.actionNavChatHomeToNavChatDetails())
    }


    override fun onResponse(code: Int?, response: Any?, title: String?, message: String) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDebounceClick(view: View) {

        super.onDebounceClick(view)
    }
}