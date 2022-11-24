package com.example.bytaw

import com.example.bytaw.ui.alarm.AlarmModel

interface Storage {
    fun getAlarms(): List<AlarmModel>?
    fun setAlarm(alarm: AlarmModel)
    fun removeAlarm(alarm: AlarmModel)
}