package com.study.androidbehavior

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.study.androidbehavior.view.AttrActivity
import com.study.androidbehavior.view.BelowActivity
import com.study.androidbehavior.view.LayoutActivity

/**
 * @author gaofeng
 * @date 2021/10/20
 *
 * 跳转到不同的界面测试不同的Behavior
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClick()
    }

    private fun setClick() {
        findViewById<View>(R.id.btn_attr).setOnClickListener {
            startActivity(Intent(this, AttrActivity::class.java))
        }

        findViewById<View>(R.id.btn_below).setOnClickListener {
            startActivity(Intent(this, BelowActivity::class.java))
        }

        findViewById<View>(R.id.btn_layout).setOnClickListener {
            startActivity(Intent(this, LayoutActivity::class.java))
        }

    }
}