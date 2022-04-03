package com.example.tanaman.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tanaman.data.DataSource
import com.example.tanaman.databinding.FragmentListTanamanBinding
import com.example.tanaman.ui.adapter.ListTanamanAdapter

class ListTanamanFragment : Fragment() {

    private var _binding: FragmentListTanamanBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListTanamanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListTanamanAdapter()

        binding.apply {
            recyclerView.adapter = adapter
            adapter.submitList(DataSource.tanaman)
        }
    }
}