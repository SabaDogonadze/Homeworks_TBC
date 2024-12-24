package com.example.tbchomework10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework10.databinding.ItemViewholderBinding

class ItemRecyclerView :
    ListAdapter<Items, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind()
        }
    }

    fun setData(item: MutableList<Items>) {
        submitList(item)
    }

    inner class ItemViewHolder(val binding: ItemViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            binding.apply {
                tvPrice.text = item.price.toString()
                tvTitle.text = item.title
                ivImage.setImageResource(item.image)
            }
        }
    }

}