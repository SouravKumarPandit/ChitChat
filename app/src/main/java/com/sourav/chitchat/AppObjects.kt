package com.sourav.chitchat

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.google.gson.Gson

lateinit var appGson: Gson

@SuppressLint("MissingPermission")
class AppObjects(context: Context) {

    init {
        val application = context as Application
        appGson = Gson()
    }


}