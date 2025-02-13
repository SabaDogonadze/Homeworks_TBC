package com.example.tbchomework18.data.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework18.R
import com.example.tbchomework18.databinding.FragmentHomeBinding

class LocationLoadingStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LocationLoadingStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(parent, retry)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(
        parent: ViewGroup,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fragment_home, parent, false)
    ) {
        private val binding = FragmentHomeBinding.bind(itemView)

        fun bind(loadState: LoadState) {
            binding.apply {
                loader.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState is LoadState.Error

                if (loadState is LoadState.Error) {
                    tvErrorMessage.text = loadState.error.localizedMessage
                    btnRetry.setOnClickListener { retry.invoke() } // retry should be stored as a property in constructor
                }
            }
        }
    }
}