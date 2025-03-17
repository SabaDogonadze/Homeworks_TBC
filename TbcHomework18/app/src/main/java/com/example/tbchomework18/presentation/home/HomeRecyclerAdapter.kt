package com.example.tbchomework18.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbchomework18.databinding.UserDataViewholderBinding

class HomeRecyclerAdapter:  PagingDataAdapter<UserModelUi, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<UserModelUi>() {
    override fun areItemsTheSame(oldItem: UserModelUi, newItem: UserModelUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserModelUi, newItem: UserModelUi): Boolean {
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
        fun bind(userData: UserModelUi) {
            binding.apply {
                tvUserEmail.text = userData.email
                tvUserFirstName.text = userData.firstName
                tvUserLastName.text = userData.lastName
                Glide.with(itemView.context).load(userData.avatar).into(ivUserImage)

            }
        }
    }
}