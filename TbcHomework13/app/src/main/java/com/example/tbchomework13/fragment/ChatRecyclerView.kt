package com.example.tbchomework13.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework13.databinding.ChatMessageFriendViewholderBinding
import com.example.tbchomework13.databinding.ChatMessageUserViewholderBinding
import com.example.tbchomework13.model.ChatMessage
import com.example.tbchomework13.model.MessageType

class ChatRecyclerView : ListAdapter<ChatMessage, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<ChatMessage>() {
    override fun areItemsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatMessage, newItem: ChatMessage): Boolean {
        return oldItem == newItem
    }

}) {

    fun setData(item: MutableList<ChatMessage>) {
        submitList(item)
    }

    companion object {
        const val USER = 1
        const val FRIEND = 2
    }

    override fun getItemViewType(position: Int): Int {
        if (currentList[position].type == MessageType.USER) {
            return USER
        } else return FRIEND
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FRIEND) {
            ChatMessageFriendViewHolder(
                ChatMessageFriendViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else ChatMessageUserViewHolder(
            ChatMessageUserViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        if (holder is ChatMessageFriendViewHolder) {
            holder.bind(item)
        }
        if (holder is ChatMessageUserViewHolder) {
            holder.bind(item)
        }
    }

    inner class ChatMessageFriendViewHolder(private var binding: ChatMessageFriendViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatMessage) {
            binding.apply {
                etMessage.text = item.message
                tvMessageTime.text = item.date
            }
        }
    }

    inner class ChatMessageUserViewHolder(private var binding: ChatMessageUserViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatMessage) {
          binding.apply {
              etMessage.text = item.message
              tvMessageTime.text = item.date
          }
        }
    }


}