package com.example.bytaw.ui.alarm

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bytaw.Storage
import javax.inject.Inject

class AlarmViewModel @Inject constructor(): ViewModel(), Parcelable {
    private lateinit var storage: Storage

    private val _text = MutableLiveData<String>().apply {
        value = "アラーム画面"
    }
    val text: LiveData<String> = _text

    constructor(parcel: Parcel) : this() {

    }

    fun getAlarms(): List<AlarmModel>? {
        return storage.getAlarms()
    }
    fun setAlarm(alarm: AlarmModel) {
        storage.setAlarm(alarm)
    }
//    fun removeAlarm(alarm: AlarmModel) {
//        storage.removeAlarm(alarm)
//    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlarmViewModel> {
        override fun createFromParcel(parcel: Parcel): AlarmViewModel {
            return AlarmViewModel(parcel)
        }

        override fun newArray(size: Int): Array<AlarmViewModel?> {
            return arrayOfNulls(size)
        }
    }
}