package com.example.healthylife.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthylife.R
import com.example.healthylife.data.HealthyLifeApplication
import com.example.healthylife.databinding.FragmentCheckBinding
import com.example.healthylife.databinding.FragmentLoadExerciseBinding
import com.example.healthylife.ui.check.CheckFragmentDirections
import com.example.healthylife.ui.check.CheckListAdapter
import com.example.healthylife.ui.check.CheckViewModel
import com.example.healthylife.ui.check.CheckViewModelFactory


class LoadExerciseFragment : Fragment() {

    private val viewModel: CheckViewModel by activityViewModels {
        CheckViewModelFactory(
            (activity?.application as HealthyLifeApplication).databaseExercise.exerciseDao()
        )
    }


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

        val adapter = CheckListAdapter {
            val action =
                CheckFragmentDirections.actionNavigationCheckToCheckDetailFragment(it.id)
            this.findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = adapter

        binding.fragmentLoadExerciseButton.setOnClickListener() {
            val action=LoadExerciseFragmentDirections.actionLoadExerciseFragmentToNavigationHome()
            this.findNavController().navigate(action)

        }
    }


}



