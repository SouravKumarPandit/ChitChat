package com.sourav.chitchat.ui.details

import com.sourav.chitchat.ui.home.data.ChatHomeRepository
import com.sourav.chitchat.base.BaseViewModel
import com.sourav.chitchat.ui.home.IChatHome


class ChatDetailsViewModel constructor(private val repository: ChatHomeRepository) :
    BaseViewModel<IChatHome>() {
}

