package com.sourav.chitchat.views

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import com.sourav.chitchat.base.ViewConst
import com.sourav.chitchat.databinding.ViewProfileImageBinding
import com.sourav.chitchat.utils.px


class VerticalProfileImageView : FrameLayout {
    constructor(context: Context) : this(context, null, -1)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setPendingMessage(5)


    }

    private val binding by lazy {
        val layoutInflater = LayoutInflater.from(context)
        ViewProfileImageBinding.inflate(
            layoutInflater,
            this,
            true
        )
    }

    fun setPendingMessage(count: Int) {
        if (count <= 0) {
            binding.circleImageView2.layoutParams =
                ConstraintLayout.LayoutParams(ViewConst.MATCH_PARENT, 0).apply {
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID

                }
            binding.messageCount.visibility = View.GONE
        } else {
            binding.messageCount.visibility = View.VISIBLE
            binding.circleImageView2.layoutParams = ConstraintLayout.LayoutParams(45.px, 0).apply {
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
//                dimensionRatio="1:1"
                setMargins(0,3.px,0,0)

            }
            binding.messageCount.layoutParams = ConstraintLayout.LayoutParams(35.px, 35.px).apply {
                bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                topToTop = ConstraintLayout.LayoutParams.PARENT_ID
//                dimensionRatio="1:1"
//                setMargins(0,0,0,3.px)


            }
            val countBitmap= textAsBitmap("+$count",16.px.toFloat(), Color.WHITE)
            binding.messageCount.setImageBitmap(countBitmap)

        }

    }
    fun textAsBitmap(text: String, textSize: Float, textColor: Int): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setTextSize(textSize)
        paint.setColor(textColor)
        paint.setTextAlign(Paint.Align.LEFT)
        val baseline: Float = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.5f).toInt() // round
        val height = (baseline + paint.descent() + 0.5f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text, 0f, baseline, paint)
        return image
    }

}