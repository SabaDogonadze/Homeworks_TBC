package com.example.tbchomework15.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework15.databinding.CardViewholderBinding
import com.example.tbchomework15.model.CardModel

class ViewPagerAdapter(
) : ListAdapter<CardModel,RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<CardModel>(){
    override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
        return oldItem == newItem
    }

}) {

    private var onItemClicked: ((CardModel) -> Unit)? = null

    fun setonItemClickedListener(listener: (CardModel) -> Unit) {
        onItemClicked = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hold = holder as CardViewHolder
        hold.bind()
    }

    inner class CardViewHolder(private var binding:CardViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
             fun bind(){
                 val item = getItem(adapterPosition)
                binding.apply {
                   tvCardNumber1.text = item.cardNumber1.toString()
                    tvCardNumber2.text = item.cardNumber2.toString()
                    tvCardNumber3.text = item.cardNumber3.toString()
                    tvCardNumber4.text = item.cardNumber4.toString()
                    tvCardholderName.text = item.cardUserName.toString()
                   /* tvValidThru.text = item.cardValidation.toString()*/
                    tvCardExpirationDate.text = item.cardExpiration.toString()
                    cardviewUserCard.setBackgroundColor(
                        ContextCompat.getColor(root.context, item.cardBackground)
                    )
                    ivVisa.setImageResource(item.cardLogo)
                    root.setOnLongClickListener {
                        onItemClicked?.invoke(item)
                        true
                    }
                }
            }
    }
}