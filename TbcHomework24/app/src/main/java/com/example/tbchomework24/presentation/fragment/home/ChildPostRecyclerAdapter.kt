package com.example.tbchomework24.presentation.fragment.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tbchomework24.R
import com.example.tbchomework24.databinding.PostItemViewholderBinding
import com.example.tbchomework24.domain.PostDataResponse
import com.example.tbchomework24.toTime

@RequiresApi(Build.VERSION_CODES.O)
class ChildPostRecyclerAdapter : ListAdapter<PostDataResponse, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<PostDataResponse>() {
        override fun areItemsTheSame(
            oldItem: PostDataResponse,
            newItem: PostDataResponse,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostDataResponse,
            newItem: PostDataResponse,
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            PostItemViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PostViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class PostViewHolder(private val binding: PostItemViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostDataResponse) {
            binding.apply {
                tvUserName.text = item.owner.firstName + " " + item.owner.lastName
                tvUserPostDate.text =item.owner.postDate.toTime()
                tvUserPost.text = item.shareContent
                tvLikeNumber.text = item.likes.toString()
                tvCommentNumber.text = item.comments.toString()
            }
            Glide.with(binding.root.context).load(item.images?.get(0)).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).into(binding.ivFirstPhoto)
            Glide.with(binding.root.context).load(item.owner.profile).circleCrop().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).into(binding.ivUserImage)
        } // all post images are not working properly
    }

}