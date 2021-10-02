package com.study.androidbehavior

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.study.androidbehavior.widget.MovableButton

class MainActivity : AppCompatActivity() {

    private lateinit var mParent: CoordinatorLayout
    private lateinit var mButtonRemove: Button
    private lateinit var mButtonMovable: MovableButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mParent = findViewById(R.id.parent)
        mButtonRemove = findViewById(R.id.button_remove)
        mButtonMovable = findViewById(R.id.movable_button)

        mButtonRemove.setOnClickListener {
            mParent.removeView(mButtonMovable)
        }

    }
}