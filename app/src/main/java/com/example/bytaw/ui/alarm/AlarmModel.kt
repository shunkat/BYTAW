package com.example.bytaw.ui.alarm

import java.util.*

data class AlarmModel(
    val id: String,
    val hour: Int,
    val minute: Int,
    val schedules: Array<Int>?,
    val isWork: Boolean
    )
