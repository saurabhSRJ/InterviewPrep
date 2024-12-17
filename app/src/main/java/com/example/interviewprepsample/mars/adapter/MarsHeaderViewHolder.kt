package com.example.interviewprepsample.mars.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.interviewprepsample.mars.model.MarsHeaderItem
import com.example.interviewprepsample.databinding.MarsHeaderItemBinding

class MarsHeaderViewHolder(private val binding: MarsHeaderItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MarsHeaderItem) {
        with(binding) {
            tvHeader.text = item.title
        }
    }
}