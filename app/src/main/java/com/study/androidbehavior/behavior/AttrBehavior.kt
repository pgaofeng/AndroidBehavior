package com.study.androidbehavior.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.res.use
import com.study.androidbehavior.R

/**
 * @author gaofeng
 * @date 2021/9/30
 *
 * 测试获取自定义属性，可以在构造方法中去获取到在xml中设置的一些自定义属性
 */
class AttrBehavior(
    context: Context,
    attributeSet: AttributeSet
) : CoordinatorLayout.Behavior<View>(context, attributeSet) {

    private var sex: String = "默认值"

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.MovableButton_Behavior).use {
            sex = it.getString(R.styleable.MovableButton_Behavior_sex) ?: "默认值"
            println("xml中设置的app:sex属性值为：$sex")
            Toast.makeText(context, "xml中设置的app:sex属性值为：$sex", Toast.LENGTH_LONG).show()
        }
    }

}