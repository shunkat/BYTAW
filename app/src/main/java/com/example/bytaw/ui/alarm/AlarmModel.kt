package com.example.bytaw.ui.alarm

import java.util.*

data class AlarmModel(
    val id: String,
    val time: String,
    val schedules: Array<Int>?,
    val isWork: Boolean
    )
