package com.example.tbchomework12

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework12.OrdersFilterButtonsData.buttonsList
import com.example.tbchomework12.databinding.FragmentMyOrdersBinding
import com.example.tbchomework12.databinding.FragmentOrderDetailBinding
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MyOrdersFragment : Fragment() {
    private var _binding: FragmentMyOrdersBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: OrdersRecyclerView
    private lateinit var filterAdapter: ButtonFilterRecyclerView
    private var newList: MutableList<FilterButton> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        setUpFilterButtonsRecycler()
        setUpRecycler()
        listeners()
    }

    private fun listeners() {
        adapter.setonDetailsClickedListener { item ->
            if (item.status == Status.PENDING) {
                val id = item.id
                parentFragmentManager.setFragmentResult(
                    "newStatus",
                    Bundle().apply { putInt("newStatus", id) })
                openNewFragment()
            }
        }
    }

    private fun openNewFragment() {
        val destination = MyOrdersFragmentDirections.actionMyOrdersFragmentToOrderDetailFragment()
        findNavController().navigate(destination)
    }

    private fun setUpRecycler() {
        adapter = OrdersRecyclerView()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
        filterItems(Status.PENDING)
    }

    private fun setUpFilterButtonsRecycler() {
        filterAdapter = ButtonFilterRecyclerView()
        filterAdapter.onItemClickedListener { selectedButton ->
            updateButtonBackground(selectedButton)
            filterItems(selectedButton.status)
            filterAdapter.setData(newList.toMutableList())
            d("water", "$buttonsList")
        }
        binding.apply {
            buttonsRecycler.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            buttonsRecycler.adapter = filterAdapter
        }
        filterAdapter.setData(buttonsList)
    }

    private fun updateButtonBackground(selectedButton: FilterButton) {
        d("d12345", " Original List $buttonsList")
        newList = buttonsList
        d("d12345", " New List $newList")
        newList.forEach { button ->
            button.selected = if (button.id == selectedButton.id) {
                Selected.SELECTED
            } else {
                Selected.NOT_SELECTED
            }
        }
        d("d12345", " Submited New List to adapter  $newList")
    }

    private fun filterItems(status: Status) {
        val filteredList = OrdersData.ordersList.filter { it.status == status }
        adapter.setData(filteredList.toMutableList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}