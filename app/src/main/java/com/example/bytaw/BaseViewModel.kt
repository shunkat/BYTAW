package com.example.bytaw

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import javax.inject.Inject

open class BaseViewModel(application: Application): AndroidViewModel(application){
    val context = getApplication<Application>().applicationContext
}