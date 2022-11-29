package com.example.bytaw.ui.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.MainActivity
import com.example.bytaw.R
import com.example.bytaw.databinding.FragmentAlarmBinding
import database.Alarms
import kotlinx.coroutines.*
import java.util.*

class AlarmFragment : Fragment() {

    private lateinit var alarmViewModel: AlarmViewModel
    private var _binding: FragmentAlarmBinding? = null
    private var _itemAlarmAdapter = ItemAlarmAdapter()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alarmViewModel =
            ViewModelProvider(this).get(AlarmViewModel::class.java)

        _binding = FragmentAlarmBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val textView: TextView = binding.textAlarm
//        alarmViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding!!.alarmToolBar.inflateMenu(R.menu.alarm_menu)
        _binding!!.alarmToolBar.setOnMenuItemClickListener {
            showTimePickerDialog()
            true
        }
    }


    fun showTimePickerDialog() {
        val newFragment: DialogFragment = TimePickerFragment(alarmViewModel, this)
        newFragment.show(childFragmentManager,"test")
    }

    override fun onResume() {
        super.onResume()
        setupListAlarm()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupListAlarm() {
        _binding!!.rcvAlarm.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = _itemAlarmAdapter
        }
        alarmViewModel.getAlarms().observe(viewLifecycleOwner, object: Observer<List<Alarms>> {
            override fun onChanged(t: List<Alarms>?) {
                if (_itemAlarmAdapter != null) {
                    _itemAlarmAdapter?.setItem(t)
                }

                // サンプル
                        val alarmMgr: AlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        val alarmIntent: PendingIntent = Intent(activity, AlarmBroadcastReceiver::class.java).let { intent ->
                            PendingIntent.getBroadcast(context, 0, intent, 0)
                        }

                        //アラームをセット
                        val calendar: Calendar = Calendar.getInstance().apply {
                            timeInMillis = System.currentTimeMillis()
                            set(Calendar.HOUR_OF_DAY,21)
                        }

                        alarmMgr.setInexactRepeating(
                            AlarmManager.RTC_WAKEUP,
                            calendar.timeInMillis,
                            AlarmManager.INTERVAL_DAY,
                            alarmIntent
                        )

                if (t == null) return
//                for (alarm in t) {
//                    if (alarm.isWork && alarm.hour != null) {
//                        //実行するクラスを指定
//                        val alarmMgr: AlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//                        val alarmIntent: PendingIntent = Intent(activity, AlarmBroadcastReceiver::class.java).let { intent ->
//                            PendingIntent.getBroadcast(context, 0, intent, 0)
//                        }
//
//                        //アラームをセット
//                        val calendar: Calendar = Calendar.getInstance().apply {
//                            timeInMillis = System.currentTimeMillis()
//                            set(Calendar.HOUR_OF_DAY, alarm.hour)
//                        }
//
//                        alarmMgr.setInexactRepeating(
//                            AlarmManager.RTC_WAKEUP,
//                            calendar.timeInMillis,
//                            AlarmManager.INTERVAL_DAY,
//                            alarmIntent
//                        )
//                    }
//
//                }
            }
        })
    }
}