package com.example.tanaman.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.tanaman.BaseApplication
import com.example.tanaman.databinding.FragmentHistoryTanamanBinding
import com.example.tanaman.ui.adapter.HistoryTanamanAdapter
import com.example.tanaman.ui.viewmodel.TanamanViewModel
import com.example.tanaman.ui.viewmodel.TanamanViewModelFactory

class HistoryTanamanFragment : Fragment() {

    private var _binding: FragmentHistoryTanamanBinding? = null

    private val binding get() = _binding!!

    private val viewModel: TanamanViewModel by activityViewModels {
        TanamanViewModelFactory(
            (activity?.application as BaseApplication).database.tanamanDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryTanamanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryTanamanAdapter()

        viewModel.tanaman.observe(this.viewLifecycleOwner) {
            binding.apply {
                recyclerView.adapter = adapter
                adapter.submitList(it)
            }
        }

        binding.clear.setOnClickListener {
            viewModel.deleteAllHistory()
        }
    }
}