package com.example.tbchomework29.presenter.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework29.databinding.UserCardsViewholderBinding
import com.example.tbchomework29.presenter.model.UserCardsUi

class FromAccountRecyclerAdapter : ListAdapter<UserCardsUi, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<UserCardsUi>() {
        override fun areItemsTheSame(
            oldItem: UserCardsUi,
            newItem: UserCardsUi,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserCardsUi,
            newItem: UserCardsUi,
        ): Boolean {
            return oldItem == newItem
        }

    }) {

    private var onItemClicked: ((UserCardsUi) -> Unit)? = null

    fun setonItemClickedListener(listener: (UserCardsUi) -> Unit) {
        onItemClicked = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardsViewHolder(
            UserCardsViewholderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CardsViewHolder) {
            holder.bind()
        }
    }

    inner class CardsViewHolder(private val binding: UserCardsViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val card = currentList[adapterPosition]
            binding.apply{
                binding.tvCardName.text = card.accountName
                binding.tvCardNumber.text = card.accountNumber
                root.setOnClickListener {
                    onItemClicked?.invoke(card)
                }
            }
        }
    }
}