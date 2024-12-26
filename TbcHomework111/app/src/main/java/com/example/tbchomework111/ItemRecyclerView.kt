package com.example.tbchomework111

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework111.databinding.ItemDeliveryViewholderBinding

class ItemRecyclerView : ListAdapter<ItemDelivery, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<ItemDelivery>() {
    override fun areItemsTheSame(oldItem: ItemDelivery, newItem: ItemDelivery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemDelivery, newItem: ItemDelivery): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemDeliveryViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private var onEditClicked: ((ItemDelivery) -> Unit)? = null

    fun setOnEditClickListener(listener: (ItemDelivery) -> Unit) {
        onEditClicked = listener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind()
        }
    }

    fun setData(item: List<ItemDelivery>) {
        submitList(item)
    }

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ItemViewHolder(private val binding: ItemDeliveryViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = currentList[adapterPosition]
            binding.apply {
                tvLocation.text = item.location
                tvDestinationName.text = item.title
                ivDestination.setImageResource(item.icon)

                btnRadio.isChecked = adapterPosition == selectedPosition


                tvEdit.isEnabled = adapterPosition == selectedPosition
                btnRadio.setOnClickListener {

                    val previousPosition = selectedPosition
                    selectedPosition = adapterPosition


                    tvEdit.isEnabled = true


                    notifyItemChanged(previousPosition)
                    notifyItemChanged(adapterPosition)
                }
                root.setOnLongClickListener {
                    removeItemFromList(item)
                    true
                }
                tvEdit.setOnClickListener {
                    onEditClicked?.invoke(item)
                }


            }

        }
    }

    private fun removeItemFromList(item: ItemDelivery) {
        val position = currentList.indexOf(item)
        if (position != -1) {
            val updatedList = currentList.toMutableList().apply { removeAt(position) }
            submitList(updatedList)
        }
    }
}
