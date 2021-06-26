package com.sourav.chitchat.base

import android.content.Context
import android.graphics.Color
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

object ThemeConstant {

    const val backGround = 0xFF292F3F.toInt()
    const val backGroundLight = 0xFF414756.toInt()
    const val backgroundThin = 0xFF565E70.toInt()
//    const val blueBright = 0xFF03A9F1.toInt()

    const val redRibbonColor = 0xFFFC6576.toInt()
    const val redErrorColor = 0xFFE7392D.toInt()
    const val redDarkErrorColor = 0xFF442C2A.toInt()
    const val yellowDarkColor = 0xFF4D4533.toInt()
    const val greenPrimary = 0xFF00CC96.toInt()
    const val greenPrimaryDark = 0xFF1A4338.toInt()


    const val vegColor = 0xFF008300.toInt()
    const val transparent = 0x00000000.toInt()
    const val greenConfirmColor = 0xFF58D896.toInt()
    const val nonVegColor = Color.RED
    const val textBlackColor = 0xff252427.toInt()
    const val alphaBlackColor = 0xAD000000.toInt()
    const val white = Color.WHITE
    const val whiteFade = 0x4AF5F5F5.toInt()
    const val textGrayColor = Color.GRAY
    const val pinkies = 0xFFFE0076.toInt()
    const val yellow = 0xFFFFC107.toInt()
    const val darkBlue = 0xFF2D3597.toInt()
    const val logoImage = pinkies
    const val logoText = 0xFF213455.toInt()
    const val green = 0xFF03AF82.toInt()


    @ColorInt
    fun getThemedColor(context: Context, @AttrRes themeResId: Int): Int {
        val a = context.obtainStyledAttributes(null, intArrayOf(themeResId))
        try {
            return a.getColor(0, 9)
        } finally {
            a.recycle()
        }
    }
}