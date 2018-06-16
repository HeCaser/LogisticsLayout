package com.example.hepan.logistics

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.logistics_item.view.*

/**
 */
class LogisticAdapter(val data: List<String>) : RecyclerView.Adapter<LogisticAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 -> {
                //设置属性
                holder.itemView.root.state = LogisticsLayout.State.STATE_HEADER
                //字体颜色
                holder.itemView.tvTime.setTextColor(Color.RED)
                holder.itemView.tvDesc.setTextColor(Color.RED)
            }
            data.size - 1 -> holder.itemView.root.state = LogisticsLayout.State.STATE_FOOTER
            else -> holder.itemView.root.state = LogisticsLayout.State.STATE_NORMAL
        }
        holder.itemView.tvDesc.text = data[position].split("\n")[0]
        holder.itemView.tvTime.text = data[position].split("\n")[1]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.logistics_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}