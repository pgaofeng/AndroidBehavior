package com.study.androidbehavior.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.study.androidbehavior.R

/**
 * @author gaofeng
 * @date 2021/10/20
 *
 * 用于测试LayoutBehavior，界面中有两个view，一个是Button，一个是ImageView
 * ImageView会处于Button的右下角，
 */
class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
    }
}