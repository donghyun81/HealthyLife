package com.example.healthylife.ui.check


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.databinding.ItemListExerciseBinding

class CheckListAdapter(private val onItemClicked:(Exercise)->Unit):
    ListAdapter<Exercise,CheckListAdapter.CheckViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckViewHolder {
        return CheckViewHolder(
            ItemListExerciseBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: CheckViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }


    class CheckViewHolder(private var binding: ItemListExerciseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.itemListExerciseName.text = exercise.exercise
            binding.itemListExerciseSet.text = exercise.set.toString()
            binding.itemListExerciseWeight.text = exercise.set.toString()
            binding.itemListExerciseCount.text = exercise.count.toString()

        }
    }

    companion object DiffCallBack {
        private val DiffCallback = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem.exercise == newItem.exercise
            }
        }
    }
}