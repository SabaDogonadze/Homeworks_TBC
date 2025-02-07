package com.example.tbchomework22.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework22.model.OvalStatus
import com.example.tbchomework22.model.PasswordOvals
import com.example.tbchomework22.R
import com.example.tbchomework22.databinding.PasswordOvalViewholderBinding

class PasswordOvalRecyclerAdapter(private var items:List<PasswordOvals>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PasswordOvalViewHolder(PasswordOvalViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PasswordOvalViewHolder){
            return holder.bind()
        }
    }

    fun updateList(newItems: List<PasswordOvals>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class PasswordOvalViewHolder(private var binding: PasswordOvalViewholderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = items[absoluteAdapterPosition]
            val color = if (item.ovalStatus == OvalStatus.CHECKED) {
                ContextCompat.getColor(binding.ivOvalButton.context, R.color.green)
            } else {
                ContextCompat.getColor(binding.ivOvalButton.context, R.color.gray)
            }
            binding.ivOvalButton.backgroundTintList = ColorStateList.valueOf(color)
        }
    }

}