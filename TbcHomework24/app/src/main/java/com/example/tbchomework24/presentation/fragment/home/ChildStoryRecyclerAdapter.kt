package com.example.tbchomework24.presentation.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbchomework24.databinding.StoryItemViewholderBinding
import com.example.tbchomework24.domain.StoryDataResponse

class ChildStoryRecyclerAdapter:ListAdapter<StoryDataResponse,RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<StoryDataResponse>(){
    override fun areItemsTheSame(oldItem: StoryDataResponse, newItem: StoryDataResponse): Boolean {
       return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: StoryDataResponse, newItem: StoryDataResponse): Boolean {
        return  oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = StoryItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is StoryViewHolder){
            holder.bind(getItem(position))
        }
    }

    inner class StoryViewHolder(private val binding: StoryItemViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StoryDataResponse) {
            Glide.with(binding.root.context).load(item.cover).into(binding.ivBackground)
            binding.tvTitle.text = item.title
        }
    }
}