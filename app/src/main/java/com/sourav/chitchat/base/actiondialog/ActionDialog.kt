package com.sourav.chitchat.base.actiondialog

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.system.Os.close
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.sourav.chitchat.R
import com.sourav.chitchat.base.ThemeConstant
import com.sourav.chitchat.base.ViewConst
import com.sourav.chitchat.databinding.LayoutSimpleActionBinding
import com.sourav.chitchat.databinding.ViewActionButtonBinding

/*
new String(Character.toChars(Integer.parseInt(
                        str, 16)))
* */
class ActionDialog(
    private val actionType: Int,
    private val title: CharSequence? = "",
    private val iconString: CharSequence = "\ue001",
    private val message: CharSequence = "",
    private val actions: ArrayList<ActionItemDTO?>?,
    var onActionPerformed: OnActionPerformed?
) : DialogFragment() {
    private val binding by lazy {
//        val layoutInflater = LayoutInflater.from(context)
        LayoutSimpleActionBinding.inflate(layoutInflater, null, false)
    }

    //var isActionButtonClicked: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val clDialog = Dialog(requireContext(), R.style.ActionDialogTheme)
        clDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        getDialogLayout()
        clDialog.setContentView(binding.root)
        if (clDialog.window != null) {
            clDialog.window?.setBackgroundDrawable(null)
//            clDialog.window?.setWindowAnimations(R.style.DialogZoomInZoomOut)
        }
        return clDialog
    }

    private fun getDialogLayout() {
        dialogTitle = title
        dialogIconString = iconString
        dialogMessage = message
        getActionLayout()
    }

    private fun getActionLayout() {
        if (actions == null) {
            val text = getString(R.string.ok)
            addCloseButton(text)

        } else
            actions.forEach { keyValue ->
                if (keyValue == null) {
                    val text = getString(R.string.close)
                    addCloseButton(text)
                } else
                    getActionButton(keyValue)
            }
    }

    private fun addCloseButton(text: CharSequence) {
        getActionButton(
                ActionItemDTO(
                        key = ActionConstant.CLOSE,
                        textColor = ThemeConstant.textBlackColor,
                        buttonColor = ThemeConstant.white,
                        text = text
                )
        )
    }

    private fun getActionButton(
        keyValue: ActionItemDTO
    ) {
        val button = ViewActionButtonBinding.inflate(layoutInflater, null, false)
        val layoutParams = LinearLayout.LayoutParams(ViewConst.WRAP_CONTENT, ViewConst.WRAP_CONTENT)

        button.root.layoutParams =
            layoutParams
        button.root.text = keyValue.text
        if (keyValue.buttonColor != null) {
            button.root.setTextColor(ColorStateList.valueOf(keyValue.buttonColor))
        } else
            button.root.setTextColor(ColorStateList.valueOf(keyValue.textColor))

        button.root.setOnClickListener {
            onActionPerformed?.onSelectedAction(dialog, actionType, keyValue)
            dialog?.dismiss()
        }
        binding.dialogActionLayout.addView(button.root)
    }


    private var dialogTitle: CharSequence? = null
        set(value) {
            field = value
//            binding.dialogTitle.hideEmptyTextView(title)
        }

    //      textView.setText("\uf007");
    private var dialogIconString: CharSequence = "\ue0f6"
        set(value) {
            field = value
        }

    //    private var dialogSubtitle: CharSequence? = null
//        set(value) {
//            field = value
//            binding.dialogSubtitle.hideEmptyTextView(subtitle)
//        }
    private var dialogMessage: CharSequence? = null
        set(value) {
            field = value
        }

    override fun onResume() {
        super.onResume()
        dialog?.window
            ?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        dialog?.window
            ?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        var closeAction: ActionItemDTO? = null
        actions?.forEach { action ->

            if (action?.key == ActionConstant.CLOSE) {
                closeAction = action
                return@forEach
            }
        }
        if (closeAction == null) {
            closeAction = ActionItemDTO(
                key = ActionConstant.CLOSE,
                text = ""
            )
        }
        onActionPerformed?.onSelectedAction(dialog, actionType, closeAction)
        super.onDismiss(dialogInterface)

    }

    interface OnActionPerformed {
        fun onSelectedAction(dialog: Dialog?, actionType: Int, action: ActionItemDTO?)
    }

}



