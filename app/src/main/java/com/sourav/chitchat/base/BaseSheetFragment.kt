package com.sourav.chitchat.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar


abstract class BaseSheetFragment<B : ViewDataBinding, T : BaseViewModel<*>?> :
        BottomSheetDialogFragment(),
        IView {
    var moduleId: Int = 0
    open var actionHandler: IModuleHandler? = null

    lateinit var bd: B
    open val svm: T? = null
    private val mClickListener =
        View.OnClickListener { v -> onDebounceClick(v) }
    lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var mProgressBar: ProgressBar
    private lateinit var frameLayout: FrameLayout
    var rootView: ConstraintLayout? = null

    abstract fun getLayoutId(): Int
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val bottomSheetDialog = BottomSheetDialog(requireActivity())
//        bottomSheetBehavior = bottomSheetDialog.behavior
//        return bottomSheetDialog
//    }

    @Nullable
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            val view = View.inflate(context, getLayoutId(), null)
            //binding views to data binding.
            rootView = view as ConstraintLayout
//        BottomSheetBehavior.from(rootView.getChildAt(0)).setPeekHeight(400);
            rootView?.addView(initFrame())
            bd = DataBindingUtil.bind(rootView!!)!!

        }
        return bd.root
    }

    //    override fun getTheme(): Int = R.style.Theme_NoWiredStrapInNavigationBar

 /*   fun applyDebouchingClickListener(vararg views: View?) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener)
        ClickUtils.applyPressedViewScale(*views)
    }*/

    private fun showProgressBar(visibility: Boolean) {
        mProgressBar.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
//        if (visibility) frameLayout.setBackgroundColor(0x3CFFFFFF) else frameLayout.background =
//                null
        frameLayout.isClickable = visibility
    }

    override fun hideProgressBar() {
        showProgressBar(false)
    }

    override fun showProgressBar() {
        showProgressBar(true)

    }

    open fun showError(title: String?, error: String?) {
        AlerterHelper.showError(requireContext(), title ?: "Ops! Something happened", error ?: "")
    }

    open fun showToast(title: String?, message: String) {
        AlerterHelper.showToast(requireContext(), title ?: "Ops! Something happened", message)
    }

    open fun showSnackBar(message: String, statusColor: Int) {
        rootView?.let {
            val snackBar = Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            val viewGroup = snackBar.view as ViewGroup
            viewGroup.minimumHeight = 45
            viewGroup.getChildAt(0).background = null
            viewGroup.background = ColorDrawable(Color.BLACK)
            val textView = (viewGroup.getChildAt(0) as ViewGroup).getChildAt(0) as TextView
            textView.background = null
            textView.setTextColor(ThemeConstant.white)
            snackBar.setActionTextColor(Color.WHITE)
            ViewCompat.setOnApplyWindowInsetsListener(snackBar.view, null)
            snackBar.show()
        }
    }

    open fun onDebounceClick(view: View) {}

    override fun showCodeError(code: Int?, title: String?, message: String) {

    }

    private fun initFrame(): FrameLayout {
        frameLayout = FrameLayout(requireContext())
        val frameParam = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        frameParam.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
        frameParam.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
        frameParam.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        frameParam.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        frameLayout.background = null
//        frameParam.gravity = Gravity.CENTER
        frameLayout.layoutParams = frameParam
//        frameLayout.id = R.id.base_framelayout
        mProgressBar = ProgressBar(requireContext())
        mProgressBar.background = null
        mProgressBar.visibility = View.GONE
//        mProgressBar.elevation = 6f
        val pbParam: FrameLayout.LayoutParams = FrameLayout.LayoutParams(45, 45)
        pbParam.gravity = Gravity.CENTER
//        pbParam.setMargins(0, UIHelper.i25px, 0, 0)
        mProgressBar.layoutParams = pbParam
//        mProgressBar.setPadding(UIHelper.i3px, UIHelper.i3px, UIHelper.i3px, UIHelper.i3px)
        frameLayout.addView(mProgressBar)
        return frameLayout

    }

    override fun onResponse(code: Int?, response: Any?, title: String?, message: String) {
    }
}




