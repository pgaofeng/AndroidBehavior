package com.study.androidbehavior.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.study.androidbehavior.R
import com.study.androidbehavior.widget.MovableButton

/**
 * @author gaofeng
 * @date 2021/10/20
 *
 * 用于测试BelowBehavior，效果如下：
 *  1，ImageView设置了BelowBehavior
 *  2，ImageView一直跟在MovableButton的下面，并随着MovableButton而动
 *  3，点击移除按钮后，MovableButton会被从父布局移除，然后ImageView会移动到页面顶部
 */
class BelowActivity : AppCompatActivity() {
    private lateinit var mParent: CoordinatorLayout
    private lateinit var mButtonMovable: MovableButton
    private lateinit var mButtonRemove: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_below)

        mParent = findViewById(R.id.parent)
        mButtonMovable = findViewById(R.id.movable_button)
        mButtonRemove = findViewById(R.id.button_remove)
        mButtonRemove.setOnClickListener {
            mParent.removeView(mButtonMovable)
        }
    }
}