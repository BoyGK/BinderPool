package com.nullpt.binderpool.anim

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * @author BGQ
 * 跑马灯样式，彩色字体
 */
class ColorTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint by lazy { Paint() }
    private val mBackgroundPaint by lazy { Paint() }

    private var offset = 0f

    //渐变颜色
    private val mColorShader by lazy {
        LinearGradient(
            0f, height / 2f, width.toFloat(), height / 2f,
            Color.YELLOW,
            Color.BLUE,
            Shader.TileMode.CLAMP
        )
    }

    init {
        mPaint.isAntiAlias = true
        mBackgroundPaint.isAntiAlias = true
        setLayerType(LAYER_TYPE_SOFTWARE, mPaint)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        drawBackColor(canvas)
        drawText(canvas)

        postDelayed({
            offset += 10f
            if (offset >= width) {
                offset -= width
            }
            invalidate()
        }, 30L)

    }

    /**
     * 绘制文字和上层背景
     */
    private fun drawText(canvas: Canvas) {

        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.XOR)

        mPaint.style = Paint.Style.FILL
        mPaint.shader = mColorShader
        canvas.drawRect(
            -width.toFloat() + offset,
            0f,
            offset,
            height.toFloat(),
            mPaint
        )
        canvas.drawRect(offset, 0f, width.toFloat() + offset, height.toFloat(), mPaint)

        mPaint.textSize = sp2px(36f).toFloat()
        canvas.drawText("动画", paddingStart.toFloat(), height.toFloat() / 3f * 2f, mPaint)

        mPaint.xfermode = null
    }

    /**
     * 绘制颜色渐变背景
     */
    private fun drawBackColor(canvas: Canvas) {
        mBackgroundPaint.style = Paint.Style.FILL
        mBackgroundPaint.color = Color.BLACK
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mBackgroundPaint)
    }

    /**
     * 文字大小转换
     */
    private fun sp2px(spValue: Float): Int {
        val fontScale = Resources.getSystem().displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

}