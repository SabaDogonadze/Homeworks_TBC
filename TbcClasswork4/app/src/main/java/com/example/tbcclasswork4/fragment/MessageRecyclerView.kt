package com.example.tbcclasswork4.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbcclasswork4.R
import com.example.tbcclasswork4.databinding.MessageFileViewholderBinding
import com.example.tbcclasswork4.databinding.MessageTextViewholderBinding
import com.example.tbcclasswork4.databinding.MessageVoiceViewholderBinding
import com.example.tbcclasswork4.model.MessageModel

class MessageRecyclerView: ListAdapter<MessageModel,RecyclerView.ViewHolder>(object :DiffUtil.ItemCallback<MessageModel>(){
    override fun areItemsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageModel, newItem: MessageModel): Boolean {
        return oldItem == newItem
    }

}){

    companion object {
        const val TEXT = 1
        const val VOICE = 2
        const val FILE = 3
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TEXT) {
            MessageTextViewHolder(
                MessageTextViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else if(viewType == VOICE){
            MessageVoiceViewHolder(
                MessageVoiceViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }else{
            MessageFileViewHolder(
                MessageFileViewholderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MessageTextViewHolder){
            holder.bind()
        }
        if (holder is MessageVoiceViewHolder){
            holder.bind()
        }
        if (holder is MessageFileViewHolder){
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].lastMessageType == "text") {
            TEXT
        } else if(currentList[position].lastMessageType == "voice"){
            VOICE
        }else
            FILE
    }

    inner class MessageTextViewHolder(private val binding:MessageTextViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = currentList[adapterPosition]
            binding.tvMessage.text = item.lastMessage.toString()
            binding.tvTimeNumber.text = item.lastActive
            binding.tvUserName.text = item.owner
            binding.tvUnreadMessage.text = item.unreadMessages.toString()

            Glide.with(binding.root.context)
                .load(item.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivIcon)
        }
    }

    inner class MessageVoiceViewHolder(private val binding:MessageVoiceViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = currentList[adapterPosition]
            binding.tvMessage.text = item.lastMessage.toString()
            binding.tvTimeNumber.text = item.lastActive
            binding.tvUserName.text = item.owner
            binding.tvUnreadMessage.text = item.unreadMessages.toString()

            Glide.with(binding.root.context)
                .load(item.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivIcon)
        }
    }

    inner class MessageFileViewHolder(private val binding:MessageFileViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = currentList[adapterPosition]
            binding.tvMessage.text = item.lastMessage.toString()
            binding.tvTimeNumber.text = item.lastActive
            binding.tvUserName.text = item.owner
            binding.tvUnreadMessage.text = item.unreadMessages.toString()

            Glide.with(binding.root.context)
                .load(item.image)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivIcon)
        }
    }
}