package com.study.androidbehavior.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.study.androidbehavior.R
import com.study.androidbehavior.adapter.SimpleAdapter

class TouchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)

        findViewById<RecyclerView>(R.id.recycler).adapter = SimpleAdapter()
    }
}