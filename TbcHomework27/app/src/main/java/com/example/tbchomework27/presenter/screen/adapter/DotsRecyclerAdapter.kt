package com.example.tbchomework27.presenter.screen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework27.R

class DotsRecyclerAdapter : RecyclerView.Adapter<DotsRecyclerAdapter.SearchDotViewHolder>() {

    private var dotsCount: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setDots(count: Int) {
        dotsCount = count
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_dot, parent, false)
        return SearchDotViewHolder(view)
    }
    override fun onBindViewHolder(holder: SearchDotViewHolder, position: Int) {
    }
    override fun getItemCount(): Int = dotsCount
    class SearchDotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}