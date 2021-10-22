package com.study.androidbehavior.behavior

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * @author gaofeng
 * @date 2021/10/22
 *
 * 参与布局阶段，将RecyclerView置于第一个View的下面
 */
class ToBottomBehavior(
    context: Context,
    attr: AttributeSet? = null
    // 由于只给RecyclerView使用，所以这里泛型直接写了RecyclerView
) : CoordinatorLayout.Behavior<RecyclerView>(context, attr) {

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: RecyclerView,
        layoutDirection: Int
    ): Boolean {
        // 低于两个子View的时候不去布局了
        if (parent.childCount < 2) {
            return false
        }
        val firstView = parent.getChildAt(0)
        child.layout(0, firstView.measuredHeight, child.measuredWidth, child.measuredHeight)
        return true
    }

}