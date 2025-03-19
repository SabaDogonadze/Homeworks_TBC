package com.example.tbchomework27.presenter.screen.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework27.databinding.SuggestionVehicleViewholderBinding
import com.example.tbchomework27.presenter.model.SuggestionVehicleUi

class SearchSuggestionRecyclerAdapter : ListAdapter<SuggestionVehicleUi, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<SuggestionVehicleUi>() {
        override fun areItemsTheSame(
            oldItem: SuggestionVehicleUi,
            newItem: SuggestionVehicleUi,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SuggestionVehicleUi,
            newItem: SuggestionVehicleUi,
        ): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VehiclesViewHolder(
            SuggestionVehicleViewholderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VehiclesViewHolder) {
            holder.bind()
        }
    }

    inner class VehiclesViewHolder(private val binding: SuggestionVehicleViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dotsAdapter = DotsRecyclerAdapter()

        init {
            binding.dotsRecycler.adapter = dotsAdapter
        }

        fun bind() {
            val vehicle = currentList[absoluteAdapterPosition]
            binding.tvName.text = vehicle.name
            d("recylerAdapter","shemovida")
            dotsAdapter.setDots(vehicle.parentNumber)
        }
    }
}