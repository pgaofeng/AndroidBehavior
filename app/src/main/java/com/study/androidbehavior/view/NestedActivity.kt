package com.study.androidbehavior.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.study.androidbehavior.R
import com.study.androidbehavior.adapter.SimpleAdapter

/**
 * @author gaofeng
 * @date 2021/11/12
 *
 * 用于验证嵌套滑动，NestedScrollingParent3和NestedScrollingChild3
 */
class NestedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested)
        findViewById<RecyclerView>(R.id.recycler).adapter = SimpleAdapter()
    }
}