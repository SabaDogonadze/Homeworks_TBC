package com.example.tbchomework12

import android.annotation.SuppressLint
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework12.databinding.ItemButtonViewholderBinding

class ButtonFilterRecyclerView : ListAdapter<FilterButton, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<FilterButton>() {

    override fun areItemsTheSame(oldItem: FilterButton, newItem: FilterButton): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FilterButton, newItem: FilterButton): Boolean {
        return oldItem == newItem
    }

}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ButtonItemViewHolder(
            ItemButtonViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    private var onItemClicked: ((FilterButton) -> Unit)? = null

    fun onItemClickedListener(click: (FilterButton) -> Unit) {
        onItemClicked = click
    }


    fun setData(item: MutableList<FilterButton>) {
        submitList(item)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ButtonItemViewHolder) {
            holder.bind()
        }
    }

    private var selectedPosition: Int = RecyclerView.NO_POSITION

    inner class ButtonItemViewHolder(private val binding: ItemButtonViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind() {
            val item = currentList[adapterPosition]
            binding.apply {
                btnButton.text = item.text
                d("d12345","ViewHolder List --> $item")

               /* if (adapterPosition == selectedPosition) {
                    btnButton.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.background_btn_is_clicked
                    )
                    btnButton.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.white)
                    )
                } else {
                    btnButton.background = null
                    btnButton.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.black)
                    )
                }*/
                if (item.selected == Selected.SELECTED){
                    btnButton.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.background_btn_is_clicked
                    )
                    btnButton.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.white)
                    )
                }else{
                    btnButton.background = null
                    btnButton.setTextColor(
                        ContextCompat.getColor(binding.root.context, R.color.black)
                    )
                }

                btnButton.setOnClickListener {
                    onItemClicked?.invoke(item)
                    if (selectedPosition != adapterPosition) {
                        selectedPosition = adapterPosition
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}