package com.example.bytaw

import android.preference.Preference
import com.example.bytaw.ui.alarm.AlarmModel
import javax.inject.Inject

class StorageImp @Inject constructor(
) :Storage {
    private var _alarms: MutableList<AlarmModel> = mutableListOf<AlarmModel>()

    override fun getAlarms(): List<AlarmModel>? {
        return _alarms
    }

    override fun setAlarm(alarm: AlarmModel) {
        _alarms.add(alarm)
    }

    override fun removeAlarm(alarm: AlarmModel) {
        _alarms.remove(alarm)
    }


}