package com.study.androidbehavior.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.study.androidbehavior.R

/**
 * @author gaofeng
 * @date 2021/10/20
 *
 * 普通的Adapter，仅仅是用来展示一个列表，用于实验嵌套滑动
 */
class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(item)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemView is TextView) {
            holder.itemView.text = "这是第${position + 1}个Item呦"
        }
    }

    override fun getItemCount() = 100

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}