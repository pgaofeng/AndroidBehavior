package com.study.androidbehavior.widget.nested

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.view.NestedScrollingParent3
import androidx.core.view.NestedScrollingParentHelper
import androidx.core.view.ViewCompat

/**
 * @author gaofeng
 * @date 2021/11/12
 *
 * NestedScrollingParent3
 */
class NestedParent @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyle: Int = -1
) : ViewGroup(context, attr, defStyle), NestedScrollingParent3 {

    private var mTopRange = 0
    private var mBottomRange = 0
    private val mParentHelper = NestedScrollingParentHelper(this)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            when (i) {
                0 -> {
                    mTopRange = -child.measuredHeight
                    child.layout(l, t + mTopRange, r, t)
                }
                2 -> {
                    mBottomRange = child.measuredHeight
                    child.layout(l, b, r, b + mBottomRange)
                }
                else -> child.layout(l, t, r, b)
            }
        }
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScrollAccepted(child: View, target: View, axes: Int, type: Int) {
        mParentHelper.onNestedScrollAccepted(child, target, axes, type)
    }

    override fun onStopNestedScroll(target: View, type: Int) {
        mParentHelper.onStopNestedScroll(target, type)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (dyUnconsumed < 0) {
            // 手指向下滑动
            if (scrollY > mTopRange) {
                // 还差desire就会完全滚走
                val desire = mTopRange - scrollY
                println("desire: $desire")
                if (dyUnconsumed <= desire) {
                    // 只能消耗一部分
                    scrollTo(0, mTopRange)
                    consumed[1] += desire
                } else {
                    // 全部消耗完
                    scrollTo(0, scrollY + dyUnconsumed)
                    consumed[1] += dyUnconsumed
                }
            }

        } else {
            // 手指向上滑动
        }


    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
    }


    /**
     * 拓展属性，将dp转换成px
     */
    private val Number.dp
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
}