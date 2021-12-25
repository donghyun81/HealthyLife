package com.example.healthylife.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthylife.R
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.databinding.ItemListLoadExerciseBinding

class LoadExerciseAdapter(private val onItemClicked:(Exercise)->Unit) :
    ListAdapter<Exercise, LoadExerciseAdapter.LoadExerciseViewHolder>(DiffCallback.DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadExerciseViewHolder {
        return LoadExerciseViewHolder(
            ItemListLoadExerciseBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: LoadExerciseViewHolder, position: Int) {
        val current = getItem(position)

        holder.itemView.setOnClickListener {
            onItemClicked(current)
             holder.onclickExercise(true)
            }
        holder.bind(current)
    }

    class LoadExerciseViewHolder(private var binding: ItemListLoadExerciseBinding):RecyclerView.ViewHolder(binding.root)
    {
         fun bind(exercise:Exercise){
            binding.apply {
                itemListLoadExerciseName.text=exercise.exercise
                itemListLoadExerciseSet.text=exercise.set.toString()
                itemListLoadExerciseWeight.text=exercise.weight.toString()
                itemListLoadExerciseCount.text=exercise.count.toString()
                }
            }
        fun onclickExercise(click:Boolean){
            if(click) {
               binding.itemListLoadExerciseLayout.setBackgroundColor(R.drawable.selected_color_exercise)
            }
            else{
                binding.itemListLoadExerciseLayout.setBackgroundColor(R.drawable.default_color_exercise)
            }
        }

        }


    companion object DiffCallback {
        private val DiffCallback = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(
                oldItem: Exercise,
                newItem: Exercise
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Exercise,
                newItem: Exercise
            ): Boolean {
                return oldItem.exercise == newItem.exercise
            }

        }
    }
}
