package com.example.tbchomework22.adapter

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework22.model.PasswordItemType
import com.example.tbchomework22.model.PasswordItems
import com.example.tbchomework22.databinding.DeleteViewholderBinding
import com.example.tbchomework22.databinding.FingerPrintViewholderBinding
import com.example.tbchomework22.databinding.NumberViewholderBinding

class PasswordInputRecyclerAdapter(private val items:List<PasswordItems>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val NUMBER = 1
        const val FINGER_PRINT = 2
        const val DELETE = 3
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            NUMBER -> {
                NumberViewHolder(NumberViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            FINGER_PRINT -> {
                FingerPrintViewHolder(FingerPrintViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            DELETE -> {
                DeleteViewHolder(DeleteViewholderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun getItemViewType(position: Int): Int {
       return when (items[position].passwordItemType){
           PasswordItemType.NUMBER -> NUMBER
           PasswordItemType.FINGER_PRINT -> FINGER_PRINT
           PasswordItemType.DELETE -> DELETE
       }
    }

    private var onItemClicked: ((PasswordItems) -> Unit)? = null
    fun setonItemClickedListener(listener: (PasswordItems) -> Unit) {
        onItemClicked = listener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NumberViewHolder){
            return holder.bind()
        }
        if (holder is FingerPrintViewHolder){
            return holder.bind()
        }
        if (holder is DeleteViewHolder){
            return holder.bind()
        }
    }

    inner class NumberViewHolder(private var binding:NumberViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = items[absoluteAdapterPosition]
            binding.btnInput.text = item.item.toString()
            binding.btnInput.setOnClickListener {
                d("12345","item clicked in recycler")
                onItemClicked?.invoke(item)
            }
        }
    }

    inner class FingerPrintViewHolder(private var binding:FingerPrintViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = items[absoluteAdapterPosition]
            binding.imageButtonUserTouch.setImageResource(item.item)
            binding.imageButtonUserTouch.setOnClickListener {
                d("12345","item clicked in recycler")
                onItemClicked?.invoke(item)
            }
        }
    }

    inner class DeleteViewHolder(private var binding:DeleteViewholderBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val item = items[absoluteAdapterPosition]
            binding.imageButtonDelete.setImageResource(item.item)
            binding.imageButtonDelete.setOnClickListener {
                d("12345","item clicked in recycler")
                onItemClicked?.invoke(item)
            }
        }
    }
}