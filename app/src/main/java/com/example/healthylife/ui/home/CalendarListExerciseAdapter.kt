package com.example.healthylife.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthylife.data.calendar_exercise.CalendarExercise
import com.example.healthylife.databinding.ItemListCalendarExerciseBinding


class CalendarListExerciseAdapter:
    ListAdapter<CalendarExercise, CalendarListExerciseAdapter.CalendarExerciseViewHolder>
        (DiffCallback.DiffCallback){

    class CalendarExerciseViewHolder(private var binding:ItemListCalendarExerciseBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(calendarExercise:CalendarExercise){
            binding.apply {
                itemListCalendarExerciseSet.text=calendarExercise.exercise.set.toString()
                itemListCalendarExerciseWeight.text=calendarExercise.exercise.weight.toString()
                itemListCalendarExerciseCount.text=calendarExercise.exercise.count.toString()
                itemListCalendarExerciseStartButton.setOnClickListener{
                    if (itemListCalendarExerciseBreaktime.isVisible){
                        itemListCalendarExerciseBreaktime.visibility= View.INVISIBLE
                        itemListCalendarExerciseStartButton.visibility=View.VISIBLE
                    }
                    else{
                        itemListCalendarExerciseBreaktime.visibility= View.VISIBLE
                        itemListCalendarExerciseStartButton.visibility=View.INVISIBLE
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarExerciseViewHolder {
        return CalendarExerciseViewHolder(ItemListCalendarExerciseBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CalendarExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    companion object DiffCallback {
        private val DiffCallback = object : DiffUtil.ItemCallback<CalendarExercise>(){
            override fun areItemsTheSame(
                oldItem: CalendarExercise,
                newItem: CalendarExercise
            ): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(
                oldItem: CalendarExercise,
                newItem: CalendarExercise
            ): Boolean {
                return oldItem ==newItem
            }
        }
    }
}

