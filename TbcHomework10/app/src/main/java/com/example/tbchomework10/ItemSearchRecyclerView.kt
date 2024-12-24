package com.example.tbchomework10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework10.databinding.ItemAllViewholderBinding
import com.example.tbchomework10.databinding.ItemSearchViewholderBinding


class ItemSearchRecyclerView:ListAdapter<ItemSearch,RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ItemSearch>() {
    override fun areItemsTheSame(oldItem: ItemSearch, newItem: ItemSearch): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemSearch, newItem: ItemSearch): Boolean {
        return oldItem == newItem
    }

}){

    companion object {
        const val NORMAL = 1
        const val ALL = 2
    }

    private var onItemClick: ((ItemSearch) -> Unit)? = null


    fun setOnItemClickListener(listener: (ItemSearch) -> Unit) {
        onItemClick = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == NORMAL) {
            ItemNormalCategoryViewHolder(
                ItemSearchViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ItemAllCategoryViewHolder(
                ItemAllViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        if (holder is ItemNormalCategoryViewHolder){
            holder.bind(item)
        }
        if (holder is ItemAllCategoryViewHolder){
            holder.bind(item)
        }
    }

    fun setData(item:MutableList<ItemSearch>){
        submitList(item)
    }


    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].buttonType == ButtonType.NORMAL) {
            NORMAL
        } else ALL
    }

    inner class ItemNormalCategoryViewHolder(private val binding: ItemSearchViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemSearch){
            binding.apply {
                btnCategory.text = item.title
                btnCategory.setCompoundDrawablesWithIntrinsicBounds(
                    ContextCompat.getDrawable(binding.root.context, item.icon),
                    null,
                    null,
                    null
                )
                btnCategory.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }

        }
    }

    inner class ItemAllCategoryViewHolder(private val binding: ItemAllViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemSearch){
            binding.apply {
                btnAll.text = item.title
                btnAll.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }

        }
    }


}