package com.study.androidbehavior.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.study.androidbehavior.widget.MovableButton

/**
 * @author gaofeng
 * @date 2021/9/30
 *
 * 一直处在目标View的下方，跟随目标而动
 */
class BelowBehavior(
    context: Context,
    attributeSet: AttributeSet
) : CoordinatorLayout.Behavior<View>(context, attributeSet) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is MovableButton
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        child.y = dependency.height + dependency.translationY
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        Toast.makeText(parent.context, "依赖的view被移除", Toast.LENGTH_SHORT).show()
        child.y = 0F
    }

}