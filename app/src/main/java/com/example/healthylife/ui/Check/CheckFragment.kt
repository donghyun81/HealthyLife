package com.example.healthylife.ui.Check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthylife.R
import com.example.healthylife.data.ExerciseApplication
import com.example.healthylife.databinding.FragmentCheckBinding

class CheckFragment : Fragment() {

    private val viewModel: CheckViewModel by activityViewModels {
        CheckViewModelFactory(
            (activity?.application as ExerciseApplication).database.exerciseDao()
        )
    }


    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckBinding.inflate(inflater, container, false)
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

        viewModel.allExercises.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
            }
        }

        binding.fragmentCheckExerciseAddButton.setOnClickListener() {
            val action =CheckFragmentDirections.actionNavigationCheckToCheckAddFragment(
                getString(R.string.fragment_check_add_exercise_title)
            )

            this.findNavController().navigate(action)
        }
    }


}