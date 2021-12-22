package com.example.healthylife.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.healthylife.databinding.FragmentHomeBinding
import com.shrikanthravi.collapsiblecalendarview.data.Day
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import android.graphics.Color
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthylife.data.HealthyLifeApplication
import com.shrikanthravi.collapsiblecalendarview.view.OnSwipeTouchListener
import java.util.*


class HomeFragment : Fragment() {

    private val viewModel:HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (
                    activity?.application as HealthyLifeApplication).databaseCalendar.calendarExerciseDao()
        )
    }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val collapsibleCalendar: CollapsibleCalendar = binding.calendarView

        val adapter = CalendarListAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        binding.loadExercise.setOnClickListener{

        }

       binding.scrollView.setOnTouchListener(object: OnSwipeTouchListener(requireContext()){
            override fun onSwipeRight() {
                collapsibleCalendar.nextDay()
            }

            override fun onSwipeLeft() {
                collapsibleCalendar.prevDay()
            }

            override fun onSwipeTop() {
                if(collapsibleCalendar.expanded){
                    collapsibleCalendar.collapse(400)
                }
            }

            override fun onSwipeBottom() {
                if(!collapsibleCalendar.expanded){
                    collapsibleCalendar.expand(400)
                }
            }
        })

        collapsibleCalendar.setExpandIconVisible(true)
        val today = GregorianCalendar()
        today.add(Calendar.DATE, 1)
        collapsibleCalendar.selectedDay = Day(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(
            Calendar.DAY_OF_MONTH))
        collapsibleCalendar.params = CollapsibleCalendar.Params(0, 100)

        collapsibleCalendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener{
            override fun onDaySelect() {
                var t1:Toast = Toast.makeText(requireContext(), "토스트 메시지1", Toast.LENGTH_SHORT)
                t1.show()

            }

            override fun onItemClick(view: View) {

            }
            override fun onClickListener() {
                var t1:Toast = Toast.makeText(requireContext(), "토스트 메시지3", Toast.LENGTH_SHORT)
                t1.show()
            }

            override fun onDataUpdate() {
                var t1:Toast = Toast.makeText(requireContext(), "토스트 메시지4", Toast.LENGTH_SHORT)
                t1.show()
            }
            override fun onDayChanged() {
                var t1:Toast = Toast.makeText(requireContext(), "토스트 메시지5", Toast.LENGTH_SHORT)
                t1.show()
            }

            override fun onMonthChange() {
                var t1:Toast = Toast.makeText(requireContext(), "토스트 메시지6", Toast.LENGTH_SHORT)
                t1.show()
            }
            override fun onWeekChange(i: Int) {
                var t1:Toast = Toast.makeText(requireContext(), "토스트 메시지7", Toast.LENGTH_SHORT)
                t1.show()

            }
        })
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}