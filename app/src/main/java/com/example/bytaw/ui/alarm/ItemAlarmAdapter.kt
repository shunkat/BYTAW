package com.example.bytaw.ui.alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.R

class ItemAlarmAdapter(private val alarms: Array<AlarmModel>?) :
    RecyclerView.Adapter<ItemAlarmAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView
            init {
                textView = view.findViewById(R.id.alarm_time)
            }
        }
    // レイアウトの骨組み作り
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (alarms.isNullOrEmpty()) {
            viewHolder.textView.text = "空です。"
        }
        viewHolder.textView.text = alarms!![position].time ?: "98:78"
    }
    override fun getItemCount() = alarms?.size ?: 0
}