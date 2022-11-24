package com.example.bytaw.ui.alarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.R

class ItemAlarmAdapter(private val alarms: List<AlarmModel>?) :
    RecyclerView.Adapter<ItemAlarmAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wakeUpTime: TextView = view.findViewById(R.id.alarm_time)
        val repeatDays: TextView = view.findViewById(R.id.repeat_days_of_week)
    }
    // レイアウトの骨組み作り
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (alarms.isNullOrEmpty()) {
            viewHolder.wakeUpTime.text = "空です。"
            return
        }
        viewHolder.wakeUpTime.text = alarms!![position].hour.toString() + ":" + alarms!![position].minute.toString()
        var repeatDays:String? = ""
        if (alarms!![position].schedules?.contains(0) == true) { repeatDays += "日 " }
        if (alarms!![position].schedules?.contains(1) == true) { repeatDays += "月 " }
        if (alarms!![position].schedules?.contains(2) == true) { repeatDays += "火 " }
        if (alarms!![position].schedules?.contains(3) == true) { repeatDays += "水 " }
        if (alarms!![position].schedules?.contains(4) == true) { repeatDays += "木 " }
        if (alarms!![position].schedules?.contains(5) == true) { repeatDays += "金 " }
        if (alarms!![position].schedules?.contains(6) == true) { repeatDays += "土 " }
        viewHolder.repeatDays.text = repeatDays
    }
    override fun getItemCount() = alarms?.size ?: 0
}