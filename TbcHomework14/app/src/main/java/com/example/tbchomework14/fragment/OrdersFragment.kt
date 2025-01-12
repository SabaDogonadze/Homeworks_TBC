package com.example.tbchomework14.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tbchomework14.data.ItemData
import com.example.tbchomework14.databinding.FragmentOrdersBinding
import com.example.tbchomework14.model.ItemStatus
import com.google.android.material.tabs.TabLayoutMediator


class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: ItemRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }


    private fun filterOrdersByStatus(status: ItemStatus) {
        val newList = ItemData.itemData.filter { item ->
            item.status == status
        }
        itemAdapter.submitList(newList)
    }

    private fun setUp() {
        setUpRecycler()
        setUpViewPager2()
        listeners()
    }

    private fun listeners() {
        itemAdapter.setonItemClickedListener { item ->
            if (item.status == ItemStatus.COMPLETED) {
                findNavController().navigate(OrdersFragmentDirections.actionOrdersFragmentToItemDetailFragment(
                    productImage = item.image,
                    productTitle = item.name,
                    productColor = item.color,
                    productyQuantity = item.quantity,
                    productPrice = item.price
                ))
            }
        }
    }

    private fun setUpViewPager2() {
        binding.viewPager.adapter = ViewPagerAdapter()

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Active"
                1 -> "Completed"
                else -> ""
            }
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val status = if (position == 0) ItemStatus.ACTIVE else ItemStatus.COMPLETED
                filterOrdersByStatus(status)
            }
        })
    }

    private fun setUpRecycler() {
        itemAdapter = ItemRecyclerView()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = itemAdapter
        }
        itemAdapter.submitList(ItemData.itemData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}