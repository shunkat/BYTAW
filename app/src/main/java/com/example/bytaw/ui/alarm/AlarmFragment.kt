package com.example.bytaw.ui.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
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
import android.app.PendingIntent.getBroadcast
import android.widget.Toast


import android.content.Context.ALARM_SERVICE
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService







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


                if (t == null) return
                for (alarm in t) {
                    if (alarm.isWork && alarm.hour != null) {
                        // 超簡易的なアラームで良いならこれ
//                        val mainActivity: MainActivity = activity as MainActivity
//                        mainActivity.createAlarm("歯磨きしてね", alarm.hour, alarm.minute)

                        val intent = Intent(
                            context,
                            AlarmBroadcastReceiver::class.java
                        )
                        val pending = getBroadcast(
                            context, 0, intent,
                            PendingIntent.FLAG_IMMUTABLE
                        )


                        //アラームをセット
                        val calendar: Calendar = Calendar.getInstance().apply {
                            timeInMillis = System.currentTimeMillis()
                            set(Calendar.HOUR_OF_DAY, alarm.hour)
                            set(Calendar.MINUTE,alarm.minute)
                        }

                        // アラームをセットする

                        // アラームをセットする
                        val am = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager?
                        if (am != null) {
                            am.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pending)
                            Toast.makeText(
                                context,
                                "Set Alarm ", Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        })
    }
}