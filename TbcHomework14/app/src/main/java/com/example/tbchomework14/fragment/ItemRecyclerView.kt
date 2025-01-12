package com.example.tbchomework14.fragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework14.databinding.OrderItemViewholderBinding
import com.example.tbchomework14.model.ItemModel


class ItemRecyclerView :
    ListAdapter<ItemModel, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ItemModel>() {
        override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            OrderItemViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private var onItemClicked: ((ItemModel) -> Unit)? = null

    fun setonItemClickedListener(listener: (ItemModel) -> Unit) {
        onItemClicked = listener
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind()
        }
    }

    inner class ItemViewHolder(private val binding: OrderItemViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            binding.apply {
                ivItem.setImageResource(item.image)
                tvItemName.text = item.name
                tvItemColorName.text = getColorName(item.color)
                ivItemColor.setColorFilter(item.color)
                tvQuantityNumber.text = item.quantity.toString()
                tvItemStatus.text = item.status.toString()
                tvItemPrice.text = item.price.toString()
                root.setOnClickListener {
                    onItemClicked?.invoke(item)
                }
            }
        }
    }

    private fun getColorName(color: Int): String {
        return when (color) {
            Color.RED -> "Red"
            Color.BLUE -> "Blue"
            Color.GREEN -> "Green"
            Color.BLACK ->"Black"
            Color.GRAY ->"Gray"
            else -> "Unknown"
        }
    }

}