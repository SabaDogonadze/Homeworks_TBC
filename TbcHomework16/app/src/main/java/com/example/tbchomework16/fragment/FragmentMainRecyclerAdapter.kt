package com.example.tbchomework16.fragment

import android.annotation.SuppressLint
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework16.databinding.ParentRecyclerViewholderBinding
import com.example.tbchomework16.model.Data
import com.example.tbchomework16.model.Response


class FragmentMainRecyclerAdapter :
    ListAdapter<Response, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Response>() {
        override fun areItemsTheSame(oldItem: Response, newItem: Response): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Response, newItem: Response): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ParentRecyclerViewHolder(
            ParentRecyclerViewholderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ParentRecyclerViewHolder) {
            holder.bind()
        }
    }

    private var onItemClickedParentRecycler: ((Data) -> Unit)? = null

    fun setOnItemClickedParentRecyclerListener(listener: (Data) -> Unit) {
        onItemClickedParentRecycler = listener
    }

    private lateinit var nestedAdapter: MainFragmentChildAdapterListAdapter

    inner class ParentRecyclerViewHolder(private var binding: ParentRecyclerViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = getItem(adapterPosition)

            nestedAdapter = MainFragmentChildAdapterListAdapter()
            binding.nestedRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            binding.nestedRecyclerView.adapter = nestedAdapter
            nestedAdapter.submitList(item.items)
            d("outerAdapter", "$item ")
            nestedAdapter.setOnItemClickedListener {
                onItemClickedParentRecycler?.invoke(it)
                d("in outer recycler","${it}")
            }
        }

      /*  private fun listener(){
            nestedAdapter.setOnItemClickedListener {
                onItemClickedParentRecycler?.invoke(it)
            }
        }*/
    }
}

