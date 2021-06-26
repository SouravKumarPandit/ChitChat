package com.sourav.chitchat.ui.details.data

import com.sourav.chitchat.source.BaseDataSource
import com.sourav.chitchat.ui.home.ChatHomeServiceAPI

class ChatDetailsRemoteDataSource constructor(
    private val serviceAPI: ChatHomeServiceAPI
) :
    BaseDataSource() {
   /* suspend fun loginSendOtp(sendOtpDTO: SendOtpDTO) = getResult {
        serviceAPI.loginSendOtp(sendOtpDTO)
    }*/


}
