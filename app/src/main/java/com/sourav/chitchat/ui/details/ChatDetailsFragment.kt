package com.sourav.chitchat.ui.details

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.laalsa.riderhive.ui.screen.login.ChatHomeViewModel
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.rubensousa.decorator.LinearMarginDecoration
import com.sourav.chitchat.R
import com.sourav.chitchat.base.BaseFragment
import com.sourav.chitchat.databinding.FragmentChatDetailsBinding
import com.sourav.chitchat.databinding.FragmentChatHomeBinding
import com.sourav.chitchat.ui.details.items.*
import com.sourav.chitchat.ui.home.ChatHomeServiceAPI
import com.sourav.chitchat.ui.home.data.ChatHomeRemoteDataSource
import com.sourav.chitchat.ui.home.data.ChatHomeRepository
import com.sourav.chitchat.utils.px


class ChatDetailsFragment : BaseFragment<FragmentChatDetailsBinding, ChatHomeViewModel>(), IChatDetails {
    override val fvm: ChatHomeViewModel =
        ChatHomeViewModel(ChatHomeRepository(ChatHomeRemoteDataSource(object : ChatHomeServiceAPI {})))


    override fun getLayoutId() = R.layout.fragment_chat_details

    //save our FastAdapter
    private  lateinit var  fastAdapter: GenericFastItemAdapter
//    private  lateinit var footerAdapter: GenericItemAdapter

    private val navController by lazy {
        findNavController()
    }

    override fun onFragmentReady(view: View) {
//        bd.loginViewmodel = fvm
//        listModuleListener = ListModuleListener()
//        bd.recyclerView = EmptyStateRecyclerView(context)
//        bd.recyclerView.onStateChangedListener = this
//        refreshLayout = SwipeRefreshLayout(context)
        fastAdapter = FastItemAdapter()
//        footerAdapter = ItemAdapter.items()
//        footerAdapter.add(HomeEndItem())
//        bd.recyclerView.addItemDecoration(VerticalItemDecoration(3.px, false))
        bd.recyclerView.addItemDecoration(
            LinearMarginDecoration(
                leftMargin = 16.px,
                topMargin = 12.px,
                rightMargin = 16.px,
                bottomMargin = 16.px,
                orientation = RecyclerView.VERTICAL
            )
        )

        fastAdapter.add(ChatDateItemBinding("3 MAR 13:34"))
        fastAdapter.add(ChatReceivedItemBinding("Anybody affected by coronavirus?"))
        fastAdapter.add(ChatSendItemBinding("At out office 3 ppl are infected. We work from home."))
        fastAdapter.add(ChatReceivedItemBinding("All good here. We wash hands and stay home."))
        fastAdapter.add(ChatImageSendItemBinding("All good here. We wash hands and stay home."))
        fastAdapter.add(ChatSendItemBinding("This is our new manager, She will join chat. Her name is Ola."))
        fastAdapter.add(ChatJoinedInfoItemBinding("Marissa joined."))
        fastAdapter.add(ChatReceivedItemBinding("Hello everybody! I’m Ola."))
        fastAdapter.add(ChatReceivedItemBinding("Hi Ola!"))
        bd.recyclerView.adapter= fastAdapter
    }

    override fun navToOtp() {


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