package com.sourav.chitchat.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<out T : IView> : ViewModel() {
    private var view: IView? = null

    @Suppress("UNCHECKED_CAST")
    fun getView(): T? {
        if (view != null)
            return try {
                view!! as T
            } catch (e: Exception) {
                null
            }
        return null
    }

    fun attachView(iView: IView) {
        this.view = iView
    }

    fun detachView() {
        this.view = null
    }


    fun changeUI(uiScope: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) { uiScope.invoke() }
    }

    fun launch(networkScope: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) { view?.showProgressBar() }
        viewModelScope.launch(Dispatchers.IO) { networkScope.invoke() }
        viewModelScope.launch(Dispatchers.Main) { view?.hideProgressBar() }
    }


}
