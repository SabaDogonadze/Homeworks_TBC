package com.example.tbchomework18.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbchomework18.data.remote.UserModel
import com.example.tbchomework18.databinding.UserDataViewholderBinding

class HomeRecyclerAdapter:  PagingDataAdapter<UserModel, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }

}) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserDataViewHolder) {
            getItem(position)?.let { holder.bind(it) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserDataViewHolder(
            UserDataViewholderBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    inner class UserDataViewHolder(private val binding: UserDataViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userData: UserModel) {
            binding.apply {
                tvUserEmail.text = userData.email
                tvUserFirstName.text = userData.firstName
                tvUserLastName.text = userData.lastName
                Glide.with(itemView.context).load(userData.avatar).into(ivUserImage)

            }
        }
    }
}