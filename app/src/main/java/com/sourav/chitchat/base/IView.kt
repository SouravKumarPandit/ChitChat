package com.sourav.chitchat.base

interface IView {
    fun showProgressBar()
    fun hideProgressBar()
    fun showCodeError(code: Int?, title: String? = "", message: String = "")
    fun onResponse(code: Int?, response: Any?, title: String? = "", message: String = "")
}