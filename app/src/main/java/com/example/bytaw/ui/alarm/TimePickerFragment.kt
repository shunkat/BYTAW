package com.example.bytaw.ui.alarm

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import database.Alarms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class TimePickerFragment(private var alarmViewModel: AlarmViewModel, private var alarmFragment: AlarmFragment) : DialogFragment(), TimePickerDialog.OnTimeSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)


        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user
        val newAlarm = Alarms(
            id=0,
            hour = hourOfDay,
            minute = minute,
            isRepeatable = false,
            isSundayAlarm = false,
            isMondayAlarm = false,
            isTuesdayAlarm = false,
            isWednesdayAlarm = false,
            isThursdayAlarm = false,
            isFridayAlarm = false,
            isSaturdayAlarm = false,
            isWork = true
        )

        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {
                alarmViewModel.addAlarm(alarm = newAlarm)
            }
        }
    }
}