package com.example.healthylife.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthylife.data.calendar_exercise.CalendarExercise
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.databinding.ItemListCalendarBinding


class CalendarListAdapter:ListAdapter<CalendarExercise,CalendarListAdapter.CalendarViewHolder>(DiffCallback.DiffCallback){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarViewHolder {
        return CalendarViewHolder(
            ItemListCalendarBinding.inflate(LayoutInflater.from(
                parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder:CalendarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class CalendarViewHolder(private var binding: ItemListCalendarBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(calendarExercise: CalendarExercise){
            binding.itemListCalendarName.text=calendarExercise.exercise.exercise

        }

    }



    companion object DiffCallback {
        private val DiffCallback = object :DiffUtil.ItemCallback<CalendarExercise>(){
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
                return oldItem.selectedDay ==newItem.selectedDay
            }

        }
    }



}


