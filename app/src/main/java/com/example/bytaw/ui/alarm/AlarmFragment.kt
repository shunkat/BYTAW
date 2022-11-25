package com.example.bytaw.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.R
import com.example.bytaw.databinding.FragmentAlarmBinding
import database.Alarms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class AlarmFragment : Fragment() {

    private lateinit var alarmViewModel: AlarmViewModel
    private var _binding: FragmentAlarmBinding? = null
    private var _itemAlarmAdapter: ItemAlarmAdapter?= null

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
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                setupListAlarm()
            }
        }
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
        val newFragment: DialogFragment = TimePickerFragment()
        newFragment.show(childFragmentManager,"test")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun setupListAlarm() {
//        alarmViewModel.setAlarm(AlarmModel("test",12,23, arrayOf(1,2),true))
        val sampleAlarms:List<Alarms> = listOf(Alarms(0,2,3,false,true,true,true,true,true,true,true,true),Alarms(0,2,3,true,true,true,true,true,true,true,true,true))
//        _itemAlarmAdapter = ItemAlarmAdapter(alarmViewModel.getAlarms())
        var alarms = alarmViewModel.getAlarms()
        if (alarms.size == 0) alarms = sampleAlarms
        _itemAlarmAdapter = ItemAlarmAdapter(alarms)
        _binding!!.rcvAlarm.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = _itemAlarmAdapter
        }
    }
}