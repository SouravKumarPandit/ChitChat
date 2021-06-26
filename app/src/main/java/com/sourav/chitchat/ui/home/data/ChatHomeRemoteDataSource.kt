package com.sourav.chitchat.ui.home.data

import com.sourav.chitchat.source.BaseDataSource
import com.sourav.chitchat.ui.home.ChatHomeServiceAPI

class ChatHomeRemoteDataSource constructor(
    private val serviceAPI: ChatHomeServiceAPI
) :
    BaseDataSource() {
   /* suspend fun loginSendOtp(sendOtpDTO: SendOtpDTO) = getResult {
        serviceAPI.loginSendOtp(sendOtpDTO)
    }*/


}
