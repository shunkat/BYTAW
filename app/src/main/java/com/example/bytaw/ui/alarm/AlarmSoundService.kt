package com.example.bytaw.ui.alarm

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import database.Alarms

class AlarmSoundService:Service(), MediaPlayer.OnCompletionListener {
    lateinit var alarm: Alarms
    companion object {
        var MeiaAlarmPlayer: MediaPlayer
    }

    var intent = Intent(AlarmSoundService::javaClass)
    override fun onBind(p0: Intent?): IBinder? {
        p0
    }
}