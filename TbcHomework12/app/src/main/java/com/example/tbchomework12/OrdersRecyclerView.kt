package com.example.tbchomework12

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework12.databinding.OrderItemViewholderBinding

class OrdersRecyclerView :
    ListAdapter<OrdersItem, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<OrdersItem>() {
        override fun areItemsTheSame(oldItem: OrdersItem, newItem: OrdersItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OrdersItem, newItem: OrdersItem): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OrderItemViewHolder(
            OrderItemViewholderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    private var onDetailsClicked: ((OrdersItem) -> Unit)? = null

    fun setonDetailsClickedListener(listener: (OrdersItem) -> Unit) {
        onDetailsClicked = listener
    }
    fun setData(item:MutableList<OrdersItem>){
        submitList(item)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OrderItemViewHolder){
            holder.bind()
        }
    }


    inner class OrderItemViewHolder(private val binding: OrderItemViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            binding.apply {
                tvOrderNumber.text = item.orderId.toString()
                tvTrackingNumber.text = item.trackingNumber
                tvQuantity.text = item.quantity.toString()
                tvStatus.text = item.status.toString()
                tvOrderDate.text = item.date.toString()
                tvPrice.text = item.price.toString()

                if (item.status == Status.CANCELLED){
                    tvStatus.setTextColor( ContextCompat.getColor(binding.root.context, R.color.red))
                }
                if (item.status == Status.DELIVERED){
                    tvStatus.setTextColor( ContextCompat.getColor(binding.root.context, R.color.green))
                }
                if (item.status == Status.PENDING){
                    tvStatus.setTextColor( ContextCompat.getColor(binding.root.context, R.color.orange))
                }
                btnDetail.setOnClickListener {
                    onDetailsClicked?.invoke(item)
                }
            }
        }
    }
}