



// it is not necessary to write this class separately. it is written in LocationLoadingAdapter as a inner class






/*
package com.example.tbchomework20.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework20.R
import com.example.tbchomework20.databinding.FragmentHomeBinding

class LoadStateViewHolder(
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
*/
