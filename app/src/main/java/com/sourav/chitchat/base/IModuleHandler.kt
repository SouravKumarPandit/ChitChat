package com.sourav.chitchat.base
interface IModuleHandler : IView {
    fun onSuccess(actionId: Int, data: Any?)

}