package com.example.interviewprepsample.mars.adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.interviewprepsample.mars.model.MarsContent
import com.example.interviewprepsample.databinding.MarsPhotoLayoutBinding

class MarsContentViewHolder(private val binding: MarsPhotoLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: MarsContent) {
        binding.progressBar.isVisible = true
        Glide.with(binding.root.context)
            .load(item.url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

            })
            .into(binding.ivImage)
    }
}