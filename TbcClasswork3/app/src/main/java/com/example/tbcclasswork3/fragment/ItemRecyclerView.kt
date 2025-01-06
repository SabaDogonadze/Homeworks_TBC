package com.example.tbcclasswork3.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbcclasswork3.R
import com.example.tbcclasswork3.databinding.ItemEmptyViewholderBinding
import com.example.tbcclasswork3.databinding.ItemOViewholderBinding
import com.example.tbcclasswork3.databinding.ItemXViewholderBinding
import com.example.tbcclasswork3.model.ButtonType
import com.example.tbcclasswork3.model.ItemModel

class ItemRecyclerView : ListAdapter<ItemModel, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<ItemModel>() {
    override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
        return oldItem == newItem
    }

}) {

    fun setData(item: MutableList<ItemModel>) {
        submitList(item)
    }


    private var onItemClicked: ((ItemModel) -> Unit)? = null

    fun setonItemClickedListener(listener: (ItemModel) -> Unit) {
        onItemClicked = listener
    }

    companion object {
        const val X = 1
        const val O = 2
        const val EMPTY = 3
    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].type == ButtonType.X_BUTTON) {
            return X
        } else if (currentList[position].type == ButtonType.O_BUTTON) {
            return O
        } else return EMPTY
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == X) {
            GameButtonXViewHolder(
                ItemXViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == O) {
            GameButtonOViewHolder(
                ItemOViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else return GameButtonEmptyViewHolder(
            ItemEmptyViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        if (holder is GameButtonOViewHolder) {
            holder.bind(item)
        }
        if (holder is GameButtonXViewHolder) {
            holder.bind(item)
        }
        if (holder is GameButtonEmptyViewHolder) {
            holder.bind(item)
        }

    }

    inner class GameButtonOViewHolder(private var binding: ItemOViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemModel) {
            binding.apply {
                btnGameButton.setImageResource(R.drawable.icon_o)
                btnGameButton.setOnClickListener {
                    onItemClicked?.invoke(item)
                }
            }
        }
    }

    inner class GameButtonXViewHolder(private var binding: ItemXViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemModel) {
            binding.apply {
                btnGameButton.setImageResource(R.drawable.icon_x)
                btnGameButton.setOnClickListener {
                    onItemClicked?.invoke(item)
                }
            }
        }
    }

    inner class GameButtonEmptyViewHolder(private var binding: ItemEmptyViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemModel) {
            binding.apply {
                btnGameButton.setOnClickListener {
                    onItemClicked?.invoke(item)
                }
            }
        }
    }


}