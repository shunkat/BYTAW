package com.example.bytaw.ui.alarm

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.R
import com.example.bytaw.databinding.FragmentAlarmBinding
import database.Alarms
import kotlinx.coroutines.*

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
                Log.d("hoge",t.toString())
                if (_itemAlarmAdapter != null) {
                    _itemAlarmAdapter?.setItem(t)
                }
            }
        })
    }
}