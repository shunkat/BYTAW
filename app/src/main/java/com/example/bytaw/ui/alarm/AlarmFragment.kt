package com.example.bytaw.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bytaw.databinding.FragmentAlarmBinding

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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupListAlarm() {
        _itemAlarmAdapter = ItemAlarmAdapter(null)
        _binding!!.rcvAlarm.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = _itemAlarmAdapter
        }
    }
}