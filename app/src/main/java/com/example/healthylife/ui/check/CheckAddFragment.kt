package com.example.healthylife.ui.check

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.data.HealthyLifeApplication
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
            (activity?.application as HealthyLifeApplication).databaseExercise.exerciseDao()
        )
    }


    lateinit var exercise: Exercise

    private var _binding: FragmentCheckAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.fragmentCheckAddExerciseSave.setOnClickListener {

            addNewExercise()
        }

    }

    private fun isEntryVaild(): Boolean {
        return viewModel.isEntryVaild(
            binding.fragmentCheckExerciseAddName.text.toString(),
            binding.fragmentCheckExerciseAddSet.text.toString(),
            binding.fragmentCheckExerciseAddWeight.text.toString(),
            binding.fragmentCheckExerciseAddCount.text.toString()
        )
    }


    private fun addNewExercise() {
        if (isEntryVaild()) {
            viewModel.addNewExercise(
                binding.fragmentCheckExerciseAddName.text.toString(),
                binding.fragmentCheckExerciseAddSet.text.toString(),
                binding.fragmentCheckExerciseAddWeight.text.toString(),
                binding.fragmentCheckExerciseAddCount.text.toString()
            )
            val action = CheckAddFragmentDirections.actionCheckAddFragmentToNavigationCheck()
            findNavController().navigate(action)
        }
    }
        override fun onDestroyView() {
            super.onDestroyView()
            // Hide keyboard.
            val inputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                        InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                requireActivity().currentFocus?.windowToken,
                0
            )
            _binding = null
        }
    }

