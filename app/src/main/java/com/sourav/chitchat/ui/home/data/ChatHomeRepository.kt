package com.sourav.chitchat.ui.home.data

class ChatHomeRepository constructor(
    private val remoteSource: ChatHomeRemoteDataSource
) {
   /* fun loginSendOtp(number: String, countryCode: String, method: String) = networkLiveData(
        networkCall = {
            remoteSource.loginSendOtp("")
        }
    ).distinctUntilChanged()*/
}
