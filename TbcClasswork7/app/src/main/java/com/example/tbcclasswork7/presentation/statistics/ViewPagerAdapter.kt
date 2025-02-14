package com.example.tbcclasswork7.presentation.statistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbcclasswork7.databinding.CardViewholderBinding
import com.example.tbcclasswork7.domain.get_statistics_items.ItemsDataResponse

class ViewPagerAdapter(
) : ListAdapter<ItemsDataResponse, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ItemsDataResponse>(){
    override fun areItemsTheSame(oldItem: ItemsDataResponse, newItem: ItemsDataResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemsDataResponse, newItem: ItemsDataResponse): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hold = holder as CardViewHolder
        hold.bind()
    }

    inner class CardViewHolder(private var binding:CardViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){
            val item = getItem(absoluteAdapterPosition)
            binding.apply {
                tvPrice.text = item.price
                tvScore.text = item.reactionCount.toString()
                tvMainTitle.text = item.title
                tvCityLocation.text = item.location
                ragingBar.rating = item.rate!!
                Glide.with(root.context)
                    .load(item.cover)
                    .into(binding.ivBackground)
            }
        }
    }
}