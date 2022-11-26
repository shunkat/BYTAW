package com.example.bytaw.ui.alarm

import android.app.Application
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bytaw.BaseViewModel
import com.example.bytaw.MainActivity
import com.google.android.material.internal.ContextUtils.getActivity
import database.AlarmDatabase
import database.Alarms
import java.security.AccessControlContext
import java.security.AccessController.getContext
import javax.inject.Inject

class AlarmViewModel @Inject constructor(application: Application): BaseViewModel(application){
    private val db = AlarmDatabase.getInstance(context)
    private val dao = db.alarmDao()
    private val _text = MutableLiveData<String>().apply {
        value = "アラーム画面"
    }
    val alarms: MutableLiveData<List<Alarms>> by lazy {
        MutableLiveData<List<Alarms>>()
    }

    suspend fun getAlarms() :List<Alarms> {
        return dao.getAll()
    }

    suspend fun addAlarm(alarm: Alarms) {
        dao.insert(alarm)
    }

    suspend fun deleteAlarm(alarm: Alarms) {
        dao.delete(alarm)
    }
}