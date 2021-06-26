package com.sourav.chitchat.base

import android.content.Context

object AlerterHelper {
    @JvmStatic
    fun showError(context: Context?, title: String?, message: String?) {
       /* Alerter.create((context as AppCompatActivity?)!!)
            .setTitle(title ?: "Error")
            .setText(message!!)
            .setIcon(R.drawable.ic_info_circle)
            .setBackgroundColorRes(R.color.redErrColor)
            .show()*/
    }

    @JvmStatic
    fun showSuccess(context: Context?, title: String?, message: String?) {
    /*    Alerter.create((context as AppCompatActivity?)!!)
            .setTitle(title ?: "Success")
            .setText(message!!)
            .setIcon(R.drawable.ic_action_done)
            .setBackgroundColorRes(R.color.colorGreenAccent)
            .show()*/
    }

    @JvmStatic
    fun showWarning(context: Context?, message: String?) {
     /*   Alerter.create((context as AppCompatActivity?)!!)
            .setTitle(R.string.warning)
            .setText(message!!)
            .setIcon(R.drawable.ic_info_circle)
            .setBackgroundColorRes(R.color.colorDarkOrange)
            .show()*/
    }

    @JvmStatic
    fun showInfo(context: Context?, message: String?) {
      /*  Alerter.create((context as AppCompatActivity?)!!)
            .setTitle(R.string.info)
            .setText(message!!)
            .setIcon(R.drawable.ic_info_circle)
            .setBackgroundColorRes(R.color.colorDarkOrange)
            .show()*/
    }

    @JvmStatic
    fun showToast(context: Context?, title: CharSequence, message: CharSequence) {
     /*   val iconFontDrawable = IconFontDrawable(context, R.string.icon_check_circle)
        iconFontDrawable.setTextColor(Color.WHITE)
        Alerter.create((context as AppCompatActivity?)!!)
            .setTitle(title)
            .setText(message)
            .setIcon(iconFontDrawable)
            .setBackgroundColorRes(R.color.colorGreenAccent)
            .show()
      */
    }
}