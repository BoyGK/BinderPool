package com.nullpt.binderpool.test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ConvertUtils
import com.nullpt.binderpool.R
import kotlinx.android.synthetic.main.activity_anim.*

class AnimActivity : AppCompatActivity() {

    private var mTag = false
    private lateinit var mRootParent: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        BarUtils.setStatusBarColor(this, Color.parseColor("#FF8282CF"))

        loading2.setOnClickListener {
            if (!mTag) {
                mTag = true
                floatView()
            } else {
                mTag = false
                val root = window.decorView as ViewGroup
                root.removeView(viewRoot)
                val lp = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                mRootParent.addView(viewRoot, lp)
            }
        }

    }

    /**
     * 悬浮主界面
     */
    private fun floatView() {
        viewRoot.cameraDistance = resources.displayMetrics.heightPixels * 1.5f
        val root = window.decorView as ViewGroup

        mRootParent = viewRoot.parent as ViewGroup
        mRootParent.removeView(viewRoot)

        val lp = when (root) {
            is FrameLayout -> {
                val re = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                re.setMargins(
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f)
                )
                re
            }
            is LinearLayout -> {
                val re = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                re.setMargins(
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f)
                )
                re
            }
            is RelativeLayout -> {
                val re = RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                re.setMargins(
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f)
                )
                re
            }
            is ConstraintLayout -> {
                val re = ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                re.setMargins(
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f),
                    ConvertUtils.dp2px(50f)
                )
                re
            }
            else -> ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        root.addView(viewRoot, lp)
    }

    private var mLastX = 0f
    private var mLastY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mTag) {
            return super.onTouchEvent(event)
        }
        super.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = event.rawX
                mLastY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val offsetX = event.rawX - mLastX
                val offsetY = event.rawY - mLastY

                viewRoot.rotationX += offsetY / 50f
                viewRoot.rotationY += offsetX / 50f

                mLastX = event.rawX
                mLastY = event.rawY
            }
        }
        return true
    }
}
