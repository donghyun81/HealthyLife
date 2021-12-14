package com.example.healthylife.ui.Check

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthylife.data.Exercise
import com.example.healthylife.data.ExerciseApplication
import com.example.healthylife.databinding.FragmentCheckAddBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckAddFragment : Fragment() {

    private val viewModel: CheckViewModel by activityViewModels {
        CheckViewModelFactory(
            (activity?.application as ExerciseApplication).database.exerciseDao()
        )
    }
    private val navigationArgs:CheckDetailFragmentArgs by navArgs()

    lateinit var exercise: Exercise

    private var _binding: FragmentCheckAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentCheckAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id=navigationArgs.exerciseId
        if (id>0) {
            viewModel.retrieveExercise(id).observe(this.viewLifecycleOwner) { selectedExercise ->
                exercise = selectedExercise
                bind(exercise)
            }
        }
        else {
            binding.fragmentCheckAddExerciseSave.setOnClickListener{
                addNewExercise()
            }
            }

    }

    private fun isEntryVaild():Boolean {
        return viewModel.isEntryVaild(
        binding.fragmentCheckExerciseAddName.toString(),
            binding.fragmentCheckExerciseAddSet.toString(),
            binding.fragmentCheckExerciseAddWeight.toString(),
            binding.fragmentCheckExerciseAddCount.toString()
        )
    }

    private fun bind(exercise: Exercise) {
        binding.apply {
        fragmentCheckExerciseAddName.setText(exercise.exercise, TextView.BufferType.SPANNABLE)
            fragmentCheckExerciseAddSet.setText(exercise.set.toString(), TextView.BufferType.SPANNABLE)
            fragmentCheckExerciseAddWeight.setText(exercise.weight.toString(), TextView.BufferType.SPANNABLE)
            fragmentCheckExerciseAddCount.setText(exercise.count.toString(), TextView.BufferType.SPANNABLE)
        }
    }
    private fun addNewExercise(){
        if (isEntryVaild()) {
            viewModel.addNewExercise(
                binding.fragmentCheckExerciseAddName.toString(),
                binding.fragmentCheckExerciseAddSet.toString(),
                binding.fragmentCheckExerciseAddWeight.toString(),
                binding.fragmentCheckExerciseAddCount.toString()
            )
            val action = CheckAddFragmentDirections.actionCheckAddFragmentToNavigationCheck()
            findNavController().navigate(action)
        }
    }


}