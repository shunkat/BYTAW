package com.example.bytaw.ui.alarm

import java.util.*

data class AlarmModel(
    val id: String,
    val time: Calendar,
    val schedules: List<Int>?,
    val isWork: Boolean
    )
