package com.example.tbchomework111

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework111.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemRecyclerView
    private var item = mutableListOf<ItemDelivery>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpItemDeliveryData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }


    private fun setUp() {
        listeners()
        setUpItemRecycler()
        receiveDataFromAddNewDestinationFragment()
        receiveDataFromEditDestinationFragment()
    }

    private fun listeners() {
        binding.btnAddNewAddress.setOnClickListener {
            openNewFragment()
        }
    }

    private fun openNewFragment() {
        val direction = HomeFragmentDirections.actionHomeFragmentToAddNewDestinationFragment()
        findNavController().navigate(direction)  // navigation using NavHost
    }

    private fun receiveDataFromAddNewDestinationFragment() {
        parentFragmentManager.setFragmentResultListener(
            "newLocation",     // receiving data from other fragment, using fragmentManager,FragmentResult
            viewLifecycleOwner          // first key is for fragmentResult and second key is for bundle to get data
        ) { _, bundle ->
            val result = bundle.getParcelable<ItemDelivery>("newLocation")
            d("newLocation", "$result")
            result?.let { addItemToList(it) }   // adding recieved data to the list
            d("newLocation", "${item.size}")
        }
    }

    private fun receiveDataFromEditDestinationFragment() {
        parentFragmentManager.setFragmentResultListener(
            "editedLocation",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getParcelable<ItemDelivery>("editedLocation")
            d("editedLocation", "$result")
            result?.let { updateItemInList(it) }   // updating list with index, result is ItemDelivery Type
            d("editedLocation", "${item.size}")
        }
    }

    private fun addItemToList(newItem: ItemDelivery) {
        item.add(newItem)   // adding item to the list and then submitting this list to the adapter
        adapter.submitList(item.toList())
    }

    private fun updateItemInList(updatedItem: ItemDelivery) {
        val index = item.indexOfFirst { it.id == updatedItem.id }
        if (index != -1) {   // indexOfFirst is returning index of the item in the list, if it is not found, it returns -1
            item[index] = updatedItem    // updating list
            adapter.submitList(item.toList())
        }
    }




    private fun setUpItemRecycler() {
        adapter = ItemRecyclerView()
        adapter.setOnEditClickListener { item ->   // catching click on the tvEdit in Recycler
            val action = HomeFragmentDirections.actionHomeFragmentToEditDestinationFragment()
            val destinationName = item.title
            val location = item.location
            val icon = item.icon
            val newItem = ItemDelivery(
                id = item.id,
                icon = icon,
                title = destinationName,
                location = location
            )
            parentFragmentManager.setFragmentResult(
                "newTitle",
                Bundle().apply { putParcelable("newTitle", newItem) })
            findNavController().navigate(action)
        }
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
        adapter.setData(item)

    }

    private fun setUpItemDeliveryData() {
        item.also {
            it.add(
                ItemDelivery(
                    icon = R.drawable.ic_home,
                    title = "Home",
                    location = "Tbilisi. Chavchavadze building 21, Floor 12 "
                )
            )
            it.add(
                ItemDelivery(
                    icon = R.drawable.ic_office,
                    title = "Work",
                    location = "Tbilisi. Chavchavadze building 21, Floor 12 "
                )
            )
            it.add(
                ItemDelivery(
                    icon = R.drawable.ic_home,
                    title = "Home",
                    location = "Tbilisi. Chavchavadze building 21, Floor 12 "
                )
            )
            it.add(
                ItemDelivery(
                    icon = R.drawable.ic_office,
                    title = "Work",
                    location = "Tbilisi. Chavchavadze building 21, Floor 12 "
                )
            )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}