package com.example.tbchomework18.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbchomework18.R
import com.example.tbchomework18.data.remote.UserModel
import com.example.tbchomework18.databinding.UserViewholderBinding

class UserDataRecyclerAdapter:ListAdapter<UserModel,RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UserModel>(){
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(UserViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is UserViewHolder){
            holder.bind()
        }
    }

    inner class UserViewHolder(private val binding: UserViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = currentList[adapterPosition]
            binding.apply {
                tvUserEmail.text = item.email
                tvUserFirstName.text = item.firstName
                tvUserLastName.text = item.lastName
            }

            Glide.with(binding.root.context)
                .load(item.avatar)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivUserImage)
        }
    }
}