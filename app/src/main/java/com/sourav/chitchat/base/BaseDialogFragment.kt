package com.sourav.chitchat.base

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.sourav.chitchat.R
import com.sourav.chitchat.base.actiondialog.ActionDialog
import com.sourav.chitchat.utils.px

abstract class BaseDialogFragment<B : ViewDataBinding> : DialogFragment(), IModuleHandler {

    var moduleId: Int = 0

    open var actionHandler: IModuleHandler? = null
    open lateinit var bd: B

    private lateinit var dialogView: ViewGroup

    var handler: Handler? = null
    var update: Runnable? = null

    private lateinit var mProgressBar: ProgressBar
    private lateinit var frameLayout: FrameLayout
    private var baseActivity: BaseActivity<*, *>? = null


    private val mClickListener =
        View.OnClickListener { v -> onDebounceClick(v) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle)
        super.onCreate(savedInstanceState)
        baseActivity = requireActivity() as BaseActivity<*, *>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bd = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        dialogView = bd.root as ViewGroup
        dialogView.addView(initFrame())
        return bd.root
    }

    private fun initFrame(): FrameLayout {
        frameLayout = FrameLayout(requireContext())
        val frameParam = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        frameLayout.layoutParams = frameParam
        frameLayout.background = null
        mProgressBar = ProgressBar(requireContext())
        mProgressBar.background = null
        mProgressBar.visibility = View.GONE
        val pbParam: FrameLayout.LayoutParams = FrameLayout.LayoutParams(45.px, 45.px)
        pbParam.gravity = Gravity.CENTER
        mProgressBar.layoutParams = pbParam
        mProgressBar.setPadding(3.px, 3.px,3.px,3.px,)
        frameLayout.addView(mProgressBar)
        return frameLayout

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onDialogReady(view)

    }

    fun applyDebouchingClickListener(vararg views: View?) {
    }

    protected fun hideSoftKeyboard(activity: Activity) {
        activity.window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }

    private fun showProgressBar(visibility: Boolean) {
        mProgressBar.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
//        if (visibility) frameLayout.setBackgroundColor(0x3CFFFFFF) else frameLayout.background =
//            null
        frameLayout.isClickable = visibility
    }

    override fun hideProgressBar() {
        showProgressBar(false)
    }

    override fun showProgressBar() {
        showProgressBar(true)

    }

    open fun displayToast(message: CharSequence) {
    }

    override fun onResponse(code: Int?, response: Any?, title: String?, message: String) {


    }

    override fun showCodeError(code: Int?, title: String?, message: String) {



    }

    override fun show(manager: FragmentManager, tag: String?) {

    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }


    open fun showSnackBar(message: String, statusColor: Int = Color.BLACK) {
        val snackBar = Snackbar.make(dialogView, message, Snackbar.LENGTH_SHORT)
        val viewGroup = snackBar.view as ViewGroup
        viewGroup.minimumHeight =0
        viewGroup.getChildAt(0).background = null
        viewGroup.background = ColorDrawable(Color.BLACK)
        val textView = (viewGroup.getChildAt(0) as ViewGroup).getChildAt(0) as TextView
        textView.background = null
        textView.setTextColor(ThemeConstant.white)
        snackBar.setActionTextColor(Color.WHITE)
//        ViewCompat.setOnApplyWindowInsetsListener(snackBar.view, null)
        snackBar.show()
    }

    open fun showError(title: String?, error: String?) {
        AlerterHelper.showError(requireContext(), title ?: "Ops! Something happened", error ?: "")
    }

    open fun onDebounceClick(view: View) {

    }

    override fun onSuccess(actionId: Int, data: Any?) {

    }

    abstract fun getLayoutId(): Int

    abstract fun onDialogReady(view: View)

}