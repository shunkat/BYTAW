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
import android.os.Debug
import android.util.Log

import android.widget.TextView
import androidx.core.os.HandlerCompat.postDelayed


class AlarmBroadcastReceiver: BroadcastReceiver() {
    val handler: Handler = Handler()
    override fun onReceive(context: Context, intent: Intent) {
        val mainIntent = Intent(context, MainActivity::class.java)
            .putExtra("onReceive", true)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(mainIntent)


        // :todo 前回の歯磨き時間をkintoneから取得する処理とそれを、アラームを鳴らすかどうか判断するメソッドに渡す。
        val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, uri)

        Toast.makeText(context,"歯磨きするまでなり続けるよ", Toast.LENGTH_SHORT)    // トーストを作成する
            .show() // トーストを表示する
        ringtone.play()
        fun play() {
            handler.postDelayed({
                Log.d("hoge","handler.isCalled")
            }, 1000 * 5)
        }
      



        fun stop() {
            handler.postDelayed({
                ringtone.stop()
            },1000 * 5)
        }
        while (true) {
            stop()
            play()
        }
    }
}