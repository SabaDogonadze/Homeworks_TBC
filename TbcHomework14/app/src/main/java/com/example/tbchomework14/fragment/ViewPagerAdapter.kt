package com.example.tbchomework14.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework14.R

class ViewPagerAdapter(
) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_viewpager, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {}

    override fun getItemCount(): Int = 2

   inner class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}