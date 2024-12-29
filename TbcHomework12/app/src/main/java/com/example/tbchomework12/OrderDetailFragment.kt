package com.example.tbchomework12

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tbchomework12.databinding.FragmentMyOrdersBinding
import com.example.tbchomework12.databinding.FragmentOrderDetailBinding


class OrderDetailFragment : Fragment() {

    private var _binding: FragmentOrderDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners() {
        binding.btnSave.setOnClickListener {
            if (binding.checkBoxDelivered.isChecked && binding.checkBoxCanceled.isChecked) {
                Toast.makeText(context,
                    getString(R.string.please_select_one_status), Toast.LENGTH_SHORT).show()
            } else if (binding.checkBoxDelivered.isChecked || binding.checkBoxCanceled.isChecked) {
                getDataFromOrdersFragment()
                findNavController().popBackStack()
            }else {
                Toast.makeText(context,
                    getString(R.string.please_select_a_status), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getDataFromOrdersFragment() {
        parentFragmentManager.setFragmentResultListener(
            "newStatus",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getInt("newStatus")
            updatingOrdersData(result)
        }
    }

    private fun updatingOrdersData(itemsId: Int) {
        val updatedItem = OrdersData.ordersList.find { it.id == itemsId }

        if (updatedItem == null) {
            Toast.makeText(context, getString(R.string.invalid_item_selected), Toast.LENGTH_SHORT).show()
            return
        }

        when {
            binding.checkBoxDelivered.isChecked -> {
                updatedItem.status = Status.DELIVERED
            }

            binding.checkBoxCanceled.isChecked -> {
                updatedItem.status = Status.CANCELLED
            }
            else -> {
                Toast.makeText(context,  getString(R.string.please_select_a_status), Toast.LENGTH_SHORT).show()
                return
            }
        }

        val index = OrdersData.ordersList.indexOfFirst { it.id == itemsId }

        if (index != -1) {
            OrdersData.ordersList[index] = updatedItem
            d("12340", "Updated item: $updatedItem")
        } else {
            Toast.makeText(context,
                getString(R.string.item_not_found_in_the_list), Toast.LENGTH_SHORT).show()
        }


        d("12340", "Updated item: $updatedItem")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}