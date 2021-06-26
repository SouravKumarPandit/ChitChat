package com.sourav.chitchat.base.sheetdialog

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class BaseActionSheetDialog(private val sheetView: View) : BottomSheetDialog(sheetView.context),
    IBaseActionSheetTemplete {
    private lateinit var actionSheet: ConstraintLayout
    private var mBehavior: BottomSheetBehavior<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBehavior = BottomSheetBehavior.from<View>(sheetView)
        if (mBehavior!!.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    override fun setContentView(layoutResId: Int) {
        actionSheet =
            LayoutInflater.from(context).inflate(layoutResId, null) as ConstraintLayout
        this.setContentView(actionSheet)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams?) {
        actionSheet = view as ConstraintLayout
        view.layoutParams = params
        this.setContentView(view)
    }

    override fun setContentView(view: View) {
        super.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        actionSheet.setBackgroundColor(Color.TRANSPARENT)
    }


}