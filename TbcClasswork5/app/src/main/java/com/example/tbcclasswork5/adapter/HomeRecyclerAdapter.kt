package com.example.tbcclasswork5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbcclasswork5.R
import com.example.tbcclasswork5.databinding.UserViewholderBinding
import com.example.tbcclasswork5.model.User

class HomeRecyclerAdapter : ListAdapter<User, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            UserViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            holder.bind()
        }
    }

    inner class UserViewHolder(private val binding: UserViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[absoluteAdapterPosition]
            binding.apply {
                tvUserLastName.text = item.lastName
                tvUserFirstName.text = item.firstName
                tvAbout.text = item.about.toString()
                tvStatus.text = checkUserStatus(item)
                Glide.with(itemView.context)
                    .load(item.avatar?:"data:image/png;base64,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")
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(binding.ivUser)
            }
        }

        private fun checkUserStatus(user: User): String {
            return when {
                user.activationStatus <= 0.0 -> "inactive"
                user.activationStatus == 1.0 -> "online"
                user.activationStatus == 2.0 -> "active some minutes ago"
                user.activationStatus in 2.0..23.0 -> "active hours ago"
                else -> "unactive for a long Time"
            }
        }
    }
}
