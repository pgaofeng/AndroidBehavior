package com.study.androidbehavior.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.study.androidbehavior.widget.MovableButton

/**
 * @author gaofeng
 * @date 2021/10/18
 *
 * Behavior的自定义布局，设置了该Behavior的View会放置在第一个[MovableButton]右侧
 */
class LayoutBehavior(
    context: Context,
    attr: AttributeSet
) : CoordinatorLayout.Behavior<View>(context, attr) {

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        // 去查找是否有MovableButton
        var target: Button? = null
        for (i in 0 until parent.childCount) {
            if (parent.getChildAt(i) is Button) {
                target = parent.getChildAt(i) as Button
                break
            }
        }
        target ?: return false
        // 将child放置在movableButton的右侧
        child.layout(
            target.right,
            target.bottom,
            target.right + child.measuredWidth,
            target.bottom + child.measuredHeight
        )
        return true
    }

}