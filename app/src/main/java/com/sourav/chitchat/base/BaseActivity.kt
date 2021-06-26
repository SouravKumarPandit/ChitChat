package com.sourav.chitchat.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<B : ViewDataBinding, T : BaseViewModel<*>> :
        AppCompatActivity(), IView {
    open val navigationPadding = true
    open lateinit var bd: B
    abstract val vm: T
    private var mProgressBar: ProgressBar? = null
    private var frameLayout: FrameLayout? = null
    lateinit var rootView: ConstraintLayout


    protected fun bindView(layoutId: Int) {
        bd = DataBindingUtil.setContentView(this, layoutId)
        bd.lifecycleOwner = this


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.attachView(this)
    }


    /**warning:
     * The root of the activity container must be of type ConstraintLayout
     *  DO NOT CHANGE THIS CONSISTENCY
     * */
    override fun setContentView(layoutResID: Int) {
        rootView =
                LayoutInflater.from(this).inflate(layoutResID, null) as ConstraintLayout
        rootView.addView(initFrame())
        super.setContentView(rootView)
    }


    override fun setContentView(view: View?) {
        rootView = getConstraintLayout()
        rootView.addView(view, 0)
        rootView.addView(frameLayout, 1)
        super.setContentView(rootView)

    }


    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        rootView = getConstraintLayout()
        rootView.addView(view, 0)
        rootView.addView(frameLayout, 1)
        super.setContentView(rootView, params)
    }



    private fun showProgressBar(visibility: Boolean) {
        mProgressBar?.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
//        if (visibility) frameLayout.setBackgroundColor(0x3CFFFFFF)
//        else frameLayout.background = null
        frameLayout?.isClickable = visibility
    }

    open fun applyDebouncingClickListener(vararg views: View?) {
    }

    override fun hideProgressBar() {
        frameLayout?.setOnClickListener(null)
        showProgressBar(false)
    }

    override fun showProgressBar() {
        frameLayout?.setOnClickListener { }
        showProgressBar(true)

    }

    override fun onResponse(code: Int?, response: Any?, title: String?, message: String) {
    }


    private fun displayToast(message: CharSequence) {
    }

    private fun getConstraintLayout(): ConstraintLayout {
        val constraintLayout = ConstraintLayout(this)
        constraintLayout.layoutParams =
                LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        initFrame()
        return constraintLayout
    }

    private fun initFrame(): FrameLayout {
        frameLayout = FrameLayout(this)
        val frameParam = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        frameLayout?.background = null
        frameParam.gravity = Gravity.CENTER
        frameLayout?.layoutParams = frameParam
        mProgressBar = ProgressBar(this)
        mProgressBar?.background = null
        mProgressBar?.visibility = View.GONE
//        mProgressBar?.elevation = 6f
        val pbParam: FrameLayout.LayoutParams = FrameLayout.LayoutParams(45, 45)
        pbParam.gravity = Gravity.CENTER
        mProgressBar?.layoutParams = pbParam
        frameLayout?.addView(mProgressBar)
        return frameLayout!!

    }


    open fun showSnackBar(message: String, statusColor: Int) {
        val snackBar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
        val viewGroup = snackBar.view as ViewGroup
        viewGroup.minimumHeight = 0
        viewGroup.getChildAt(0).background = null
        /*  user Color code from ui developer*/
        viewGroup.background = ColorDrawable(Color.BLACK)
        val textView = (viewGroup.getChildAt(0) as ViewGroup).getChildAt(0) as TextView
        textView.background = null
        textView.setTextColor(ThemeConstant.white)
        snackBar.setActionTextColor(Color.WHITE)
        snackBar.show()
    }

    open fun showError(message: String) {
        val snackBar = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT)
        val viewGroup = snackBar.view as ViewGroup
        viewGroup.minimumHeight = 0
        viewGroup.background = ColorDrawable(Color.BLACK)
        val textView = (viewGroup.getChildAt(0) as ViewGroup).getChildAt(0) as TextView
        textView.setTextColor(Color.WHITE)
        textView.maxLines = 3
        textView.textSize = 12f
        textView.gravity = Gravity.CENTER
        snackBar.show()
    }


    open fun showToast(title: String?, message: String) {
        AlerterHelper.showToast(this, title ?: "", message)
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun applyDebounceClickListener(vararg views: View) {
    }

    override fun showCodeError(code: Int?, title: String?, message: String) {

    }


    private var internetDisconnected = false

    override fun onDestroy() {
        vm.detachView()
        super.onDestroy()

    }

    private val mClickListener = View.OnClickListener { onDebounceClick(it) }
    open fun onDebounceClick(view: View) {
    }
}