package com.example.healthylife.ui.Check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.healthylife.databinding.FragmentCheckBinding

class CheckFragment : Fragment() {

    private lateinit var checkViewModel: CheckViewModel
    private var _binding: FragmentCheckBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkViewModel =
            ViewModelProvider(this).get(CheckViewModel::class.java)

        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCheck
        checkViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}