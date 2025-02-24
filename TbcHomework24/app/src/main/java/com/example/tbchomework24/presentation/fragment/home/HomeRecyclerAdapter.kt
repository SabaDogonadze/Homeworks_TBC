package com.example.tbchomework24.presentation.fragment.home

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework24.databinding.HomeRecyclerViewholderBinding
import com.example.tbchomework24.domain.PostDataResponse
import com.example.tbchomework24.domain.StoryDataResponse

@RequiresApi(Build.VERSION_CODES.O)
class HomeRecyclerAdapter :
    ListAdapter<HomeItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<HomeItem>() {
        override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return when {
                oldItem is HomeItem.StoryItem && newItem is HomeItem.StoryItem -> oldItem.stories == newItem.stories
                oldItem is HomeItem.PostItem && newItem is HomeItem.PostItem -> oldItem.posts == newItem.posts
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
            return oldItem == newItem
        }

    }) {
    companion object {
        private const val VIEW_TYPE_STORY = 0
        private const val VIEW_TYPE_POST = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_STORY -> {
                StoryRecyclerViewHolder(
                    HomeRecyclerViewholderBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
            VIEW_TYPE_POST -> {
                PostRecyclerViewHolder(
                    HomeRecyclerViewholderBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is HomeItem.StoryItem -> (holder as StoryRecyclerViewHolder).bind(item.stories)
            is HomeItem.PostItem -> (holder as PostRecyclerViewHolder).bind(item.posts)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HomeItem.StoryItem -> VIEW_TYPE_STORY
            is HomeItem.PostItem -> VIEW_TYPE_POST
        }
    }

    private lateinit var storyChildAdapter: ChildStoryRecyclerAdapter

    inner class StoryRecyclerViewHolder(private val binding: HomeRecyclerViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story:List<StoryDataResponse>) {
            storyChildAdapter = ChildStoryRecyclerAdapter()
            binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
            binding.childRecyclerView.adapter = storyChildAdapter
            storyChildAdapter.submitList(story)
        }
    }

    private lateinit var postChildAdapter: ChildPostRecyclerAdapter

    inner class PostRecyclerViewHolder(private val binding: HomeRecyclerViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post:List<PostDataResponse>) {
            postChildAdapter = ChildPostRecyclerAdapter()
            binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.childRecyclerView.adapter = postChildAdapter
            postChildAdapter.submitList(post)
        }
    }

}