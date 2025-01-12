package com.example.tbchomework14.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbchomework14.R
import com.example.tbchomework14.databinding.FragmentItemDetailBinding


class ItemDetailFragment : Fragment() {
    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpItemDetails()
        listeners()
    }

    private fun listeners() {
        binding.apply {
            btnSubmit.setOnClickListener {
                val userReview = binding.etReview.text.toString()
                if (userReview.isNotEmpty()) {
                    Toast.makeText(context, userReview, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(ItemDetailFragmentDirections.actionItemDetailFragmentToOrdersFragment())
                }else{
                    Toast.makeText(context,
                        getString(R.string.your_review_is_empty), Toast.LENGTH_SHORT).show()
                }
            }
            btnCancel.setOnClickListener {
                findNavController().navigate(ItemDetailFragmentDirections.actionItemDetailFragmentToOrdersFragment())
            }
        }
    }

    private fun setUpItemDetails() {
        binding.apply {
            ivItem.setImageResource(args.productImage)
            tvItemName.text = args.productTitle
            tvItemColorName.text = getColorName(args.productColor)
            ivItemColor.setColorFilter(args.productColor)
            tvQuantityNumber.text = args.productyQuantity.toString()
            tvItemPrice.text = args.productPrice.toString()

        }
    }

    private fun getColorName(color: Int): String {
        return when (color) {
            Color.RED -> "Red"
            Color.BLUE -> "Blue"
            Color.GREEN -> "Green"
            Color.BLACK ->"Black"
            Color.GRAY ->"Gray"
            else -> "Unknown"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}