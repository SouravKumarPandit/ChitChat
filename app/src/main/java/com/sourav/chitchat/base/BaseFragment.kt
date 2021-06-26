package com.sourav.chitchat.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding, T : BaseViewModel<IView>> : Fragment(), IView {

    open lateinit var bd: B
    abstract val fvm: T
    var toolBar: Toolbar? = null

    private var baseActivity: BaseActivity<*, *>? = null
    private var fragmentView: View? = null
    private val mClickListener = View.OnClickListener { v -> onDebounceClick(v) }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun onFragmentReady(view: View)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            baseActivity = context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        bd = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        bd.lifecycleOwner = this
        fragmentView = bd.root
        fvm.attachView(this)
        onFragmentReady(bd.root)

        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    open fun applyDebounceClickListener(vararg views: View?) {
    }

    override fun showProgressBar() {
        baseActivity?.showProgressBar()
    }

    override fun onDestroy() {
        baseActivity = null
//        bd?.lifecycleOwner = null
        super.onDestroy()

    }

    override fun hideProgressBar() {
        baseActivity?.hideProgressBar()
    }

    open fun showError(title: String?, message: String) {
        baseActivity?.showToast(title, message)
    }

    open fun showSnackBar(message: String, statusColor: Int) {
        baseActivity?.showSnackBar(message, statusColor)

    }


    open fun showAlerter(title: String, message: String?) {
        AlerterHelper.showError(requireContext(), title, message)
    }

    open fun showSuccessAlerter(title: String) {
        AlerterHelper.showToast(requireContext(), title, "")
    }

//    protected fun hideToolBar() {
//        toolBar?.let { it.isGone = true }
//    }
//
//    protected fun showToolBar() {
//        toolBar?.let { it.isGone = true }
//    }

    open fun onDebounceClick(view: View) {

    }

    override fun onResponse(code: Int?, response: Any?, title: String?, message: String) {

    }

    open fun displayToast(message: CharSequence) {
    }

    override fun showCodeError(code: Int?, title: String?, message: String) {
    }


}