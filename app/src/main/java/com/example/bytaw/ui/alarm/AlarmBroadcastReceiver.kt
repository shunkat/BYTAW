package com.example.bytaw.ui.alarm

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.bytaw.MainActivity
import android.media.RingtoneManager
import android.media.Ringtone
import android.net.Uri
import android.os.Handler
import android.R
import android.app.PendingIntent
import android.os.Debug
import android.util.Log

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit


class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        CoroutineScope(Dispatchers.IO).launch {
            val retrofit = Retrofit.Builder().apply {
                baseUrl("http://3vklk68vdjrz.cybozu.com/")
            }.build()
            val service = retrofit.create(CheckService::class.java)
            val history = service.get("3")
            Log.d("hoge",history.toString())
        }

        val mainIntent = Intent(context, MainActivity::class.java)
            .putExtra("onReceive", true)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(mainIntent)


        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, uri)
//
//        val id = intent.getIntExtra("id",0)
//        val startServiceIntent = Intent(context, )
        // Retrofit本体





        if (false) {
            Toast.makeText(context,"歯磨きしてえらい！", Toast.LENGTH_LONG)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                0
            )
            pendingIntent.cancel()

        } else {
            Toast.makeText(context,"歯磨きするまでなり続けるよ", Toast.LENGTH_SHORT)    // トーストを作成する
                .show() // トーストを表示する
            ringtone.play()
        }
    }
}