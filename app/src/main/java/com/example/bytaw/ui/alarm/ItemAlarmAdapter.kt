package com.example.bytaw.ui.alarm

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.databinding.ItemAlarmBinding

class ItemAlarmAdapter(
    private val _alarms: ArrayList<AlarmModel?>,
    private val onItemClick: (id: String?) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return _alarms.size
    }

    class ItemAlarmHolder(val binding: ItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            context: Context,
            mediaType: Int,
            item: AlarmModel?,
            onItemClick: (id: String?, title: String?) -> Unit
        ) {

        }


        }
}