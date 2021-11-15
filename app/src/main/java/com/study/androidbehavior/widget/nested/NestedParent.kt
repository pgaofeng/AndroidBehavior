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
        if (dyUnconsumed < 0 && scrollY > mTopRange) {
            // 手指向下滑动
            // 还差desire就会完全滚走
            val desire = mTopRange - scrollY
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
        if (dy > 0 && scrollY < 0) {
            // 手指向上滑动
            val desire = -scrollY
            if (dy <= desire) {
                // 全部消耗
                scrollTo(0, scrollY + dy)
                consumed[1] = dy
            } else {
                // 消耗一部分
                scrollTo(0, scrollY + dy)
                consumed[1] = desire
            }
        }
    }
}