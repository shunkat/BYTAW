package com.example.bytaw.ui.alarm

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.bytaw.MainActivity

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val mainIntent = Intent(context, MainActivity::class.java)
            .putExtra("onReceive", true)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(mainIntent)

         Toast.makeText(context,"アラームを受診しました。", Toast.LENGTH_SHORT)    // トーストを作成する
            .show() // トーストを表示する

//        :todo 前回の歯磨き時間をkintoneから取得する処理とそれを、アラームを鳴らすかどうか判断するメソッドに渡す。

    }
}