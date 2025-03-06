package com.study.androidbehavior.widget

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import kotlin.math.pow

class MyViewGroup @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    def: Int = 0
) : LinearLayout(context, attributeSet, def){

    // 300dp，函数中的常量N
    private val max = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300F, context.resources.displayMetrics)

    // 手指按下的坐标
    private var downY  = 0F
    // 应当滚动的距离
    private var scrollTop = 0F

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.actionMasked) {
            // 按下时记录坐标
            MotionEvent.ACTION_DOWN -> downY = event.y
            MotionEvent.ACTION_MOVE -> {
                // 当前滚动的距离
                val deltaY = event.y - downY
                // 计算deltaY对应的实际滚动距离
                scrollTop = scrollTop + compute(scrollTop + deltaY) - compute(scrollTop)
                // 滚动到对应的位置
                scrollTo(scrollX, -scrollTop.toInt())
                // 本次滑动结束，更新坐标
                downY = event.y
            }
            MotionEvent.ACTION_UP -> {
                // 手指抬起时恢复到初始位置
                ValueAnimator.ofFloat(scrollTop, 0F).also {
                    it.interpolator = DecelerateInterpolator()
                    it.addUpdateListener { anim->
                        scrollTop = anim.animatedValue as Float
                        scrollTo(scrollX, -scrollTop.toInt())
                    }
                    it.start()
                }
            }
        }
        return true
    }

    // 映射函数：f(x) = N * (1 - 2^(-x/N))
    private fun compute(x: Float): Float {
        return max * (1 - 2F.pow(-x/max))
    }

}