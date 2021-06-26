package com.sourav.chitchat.base.actiondialog

import androidx.annotation.ColorInt
import com.sourav.chitchat.base.ThemeConstant

data class ActionItemDTO(
    val key: Int,
    val text: CharSequence,
    @ColorInt val textColor: Int = ThemeConstant.white,
    @ColorInt val buttonColor: Int? = ThemeConstant.logoImage,
    val data: MutableMap<String, Any>? = null
)