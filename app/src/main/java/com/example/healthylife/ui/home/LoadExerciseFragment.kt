package com.example.healthylife.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthylife.R
import com.example.healthylife.data.HealthyLifeApplication
import com.example.healthylife.data.exercise.Exercise
import com.example.healthylife.databinding.FragmentCheckBinding
import com.example.healthylife.databinding.FragmentLoadExerciseBinding
import com.example.healthylife.ui.check.CheckFragmentDirections
import com.example.healthylife.ui.check.CheckListAdapter
import com.example.healthylife.ui.check.CheckViewModel
import com.example.healthylife.ui.check.CheckViewModelFactory


class LoadExerciseFragment : Fragment() {

    private val exerciseViewModel: CheckViewModel by activityViewModels {
        CheckViewModelFactory(
            (activity?.application as HealthyLifeApplication).databaseExercise.exerciseDao()
        )
    }

    private val homeViewModel:HomeViewModel by activityViewModels {
        HomeViewModelFactory(
            (activity?.application as HealthyLifeApplication).databaseCalendar.calendarExerciseDao()
        )
    }


    private var loadExercises: MutableSet<Exercise> = mutableSetOf()
    private var _binding: FragmentLoadExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoadExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LoadExerciseAdapter {

            if(loadExercises.indexOf(it)==-1){
                loadExercises.add(it)
            }
            else{
                var t1: Toast = Toast.makeText(requireContext(),loadExercises.indexOf(it)
                    .toString(), Toast.LENGTH_SHORT)
                t1.show()
                loadExercises.remove(it)

            }


        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        exerciseViewModel.allExercises.observe(this.viewLifecycleOwner) { exercises ->
            exercises.let {
                    adapter.submitList(it)
            }
        }

        binding.fragmentLoadExerciseButton.setOnClickListener() {
            for (loadExercise in loadExercises) {
                homeViewModel.loadNewCalendar(
                    homeViewModel.selectedDay.value.toString(),
                    loadExercise)
            }
            val action=LoadExerciseFragmentDirections.actionLoadExerciseFragmentToNavigationHome()
            this.findNavController().navigate(action)

        }
    }

}



