package com.example.bytaw.ui.alarm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.R
import database.Alarms
import java.security.AccessController.getContext

class ItemAlarmAdapter() :
    RecyclerView.Adapter<ItemAlarmAdapter.ViewHolder>() {
    var currentAlarms: ArrayList<Alarms> = ArrayList()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wakeUpTime: TextView = view.findViewById(R.id.alarm_time)
        val repeatDays: TextView = view.findViewById(R.id.repeat_days_of_week)
    }
    val context = getContext()
    // レイアウトの骨組み作り

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (currentAlarms.isNullOrEmpty()) {
            viewHolder.wakeUpTime.text = "空です。"
            return
        }
        viewHolder.wakeUpTime.text = currentAlarms!![position].hour.toString() + ":" + currentAlarms!![position].minute.toString()
        var repeatDays:String? = ""
        if (currentAlarms!![position].isRepeatable) {
            if (currentAlarms!![position].isSundayAlarm) repeatDays += "日 "
            if (currentAlarms!![position].isMondayAlarm) repeatDays += "月 "
            if (currentAlarms!![position].isTuesdayAlarm) repeatDays += "火 "
            if (currentAlarms!![position].isWednesdayAlarm) repeatDays += "水 "
            if (currentAlarms!![position].isThursdayAlarm) repeatDays += "木 "
            if (currentAlarms!![position].isFridayAlarm) repeatDays += "金 "
            if (currentAlarms!![position].isSaturdayAlarm) repeatDays += "土 "
        }
        viewHolder.repeatDays.text = repeatDays
    }
    override fun getItemCount() = currentAlarms?.size ?: 0

    fun setItem(newAlarm: List<Alarms>?) {
        currentAlarms.clear()
        if (newAlarm.isNullOrEmpty()) return
        currentAlarms.addAll(newAlarm)
        notifyDataSetChanged()
    }

}