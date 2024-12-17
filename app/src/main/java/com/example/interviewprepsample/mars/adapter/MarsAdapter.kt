package com.example.interviewprepsample.mars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewprepsample.mars.model.MarsContent
import com.example.interviewprepsample.mars.model.MarsHeaderItem
import com.example.interviewprepsample.mars.model.UiItem
import com.example.interviewprepsample.databinding.MarsHeaderItemBinding
import com.example.interviewprepsample.databinding.MarsPhotoLayoutBinding

class MarsAdapter : ListAdapter<UiItem, RecyclerView.ViewHolder>(MarsComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MarsViewType.TEXT.ordinal -> {
                val binding = MarsHeaderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MarsHeaderViewHolder(binding)
            }

            else -> {
                val binding = MarsPhotoLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MarsContentViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MarsHeaderItem -> MarsViewType.TEXT.ordinal
            else -> MarsViewType.IMAGE.ordinal
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MarsContentViewHolder -> holder.bind(getItem(position) as MarsContent)
            is MarsHeaderViewHolder -> holder.bind(getItem(position) as MarsHeaderItem)
        }
    }
}

enum class MarsViewType {
    TEXT,
    IMAGE
}

class MarsComparator : DiffUtil.ItemCallback<UiItem>() {
    override fun areItemsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiItem, newItem: UiItem): Boolean {
        return oldItem == newItem
    }

}