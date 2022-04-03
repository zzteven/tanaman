package com.example.tanaman.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tanaman.databinding.ListHistoryTanamanBinding
import com.example.tanaman.model.Tanaman

class HistoryTanamanAdapter : ListAdapter<Tanaman, HistoryTanamanAdapter.TanamanViewHolder>(DiffCallback) {

    class TanamanViewHolder(private var binding: ListHistoryTanamanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tanaman: Tanaman) {
            binding.tanaman = tanaman
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Tanaman>() {
        override fun areItemsTheSame(oldItem: Tanaman, newItem: Tanaman): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tanaman, newItem: Tanaman): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanamanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TanamanViewHolder(ListHistoryTanamanBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: TanamanViewHolder, position: Int) {
        val tanaman = getItem(position)
        holder.bind(tanaman)
    }
}