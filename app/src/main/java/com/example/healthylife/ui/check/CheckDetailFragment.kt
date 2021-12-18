package com.example.healthylife.ui.check

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.data.HealthyLifeApplication
import com.example.healthylife.databinding.FragmentCheckDetailBinding


class CheckDetailFragment : Fragment() {

    private val viewModel:CheckViewModel by activityViewModels{
        CheckViewModelFactory(
            (activity?.application as HealthyLifeApplication).databaseExercise.exerciseDao()
        )
    }

    private val navigationArgs:CheckDetailFragmentArgs by navArgs()
    lateinit var exercise: Exercise

    private var _binding:FragmentCheckDetailBinding?=null
    private val binding get() =_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id=navigationArgs.exerciseId
        viewModel.retrieveExercise(id).observe(this.viewLifecycleOwner) {
            selectedExercise-> exercise=selectedExercise
            bind(exercise)
        }

    }

    private fun isEntryValid():Boolean {
        return viewModel.isEntryVaild(
            binding.fragmentCheckExerciseDetailName.text.toString(),
            binding.fragmentCheckExerciseDetailSet.text.toString(),
            binding.fragmentCheckExerciseDetailWeight.text.toString(),
            binding.fragmentCheckExerciseDetailCount.text.toString()
        )
    }

    private fun updateExercise() {
        if(isEntryValid()) {
            viewModel.updateExercise(
                this.navigationArgs.exerciseId,
                this.binding.fragmentCheckExerciseDetailName.text.toString(),
                this.binding.fragmentCheckExerciseDetailSet.text.toString(),
                this.binding.fragmentCheckExerciseDetailWeight.text.toString(),
                this.binding.fragmentCheckExerciseDetailCount.text.toString()
            )
            findNavController().navigateUp()
        }
    }

    private fun deleteExercise(){
        viewModel.deleteExercise(exercise)
        findNavController().navigateUp()
    }

    private fun bind(exercise: Exercise) {
        binding.apply {
            fragmentCheckExerciseDetailName.setText(exercise.exercise,TextView.BufferType.SPANNABLE)
            fragmentCheckExerciseDetailSet.setText(exercise.set.toString(),TextView.BufferType.SPANNABLE)
            fragmentCheckExerciseDetailWeight.setText(exercise.weight.toString(),TextView.BufferType.SPANNABLE)
            fragmentCheckExerciseDetailCount.setText(exercise.count.toString(),TextView.BufferType.SPANNABLE)
            fragmentCheckDetailExerciseUpdate.setOnClickListener{updateExercise()}
            fragmentCheckDetailExerciseDelete.setOnClickListener{deleteExercise()}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}