package com.example.tbchomework111

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tbchomework111.databinding.FragmentEditDestinationBinding


class EditDestinationFragment : Fragment() {

    private var _binding: FragmentEditDestinationBinding? = null
    private val binding get() = _binding!!
    private var itemList = mutableListOf<ItemDelivery>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditDestinationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        receiveDataFromFragment()
        listeners()

    }

    private fun listeners() {
            binding.btnSave.setOnClickListener {
                if(sendDataToHomeFragment()){
                sendDataToHomeFragment()
                findNavController().popBackStack()
            }}
    }

    private fun receiveDataFromFragment() {
        parentFragmentManager.setFragmentResultListener(
            "newTitle",
            viewLifecycleOwner
        ) { _, bundle ->
            val result = bundle.getParcelable<ItemDelivery>("newTitle")
            d("newTitle", "$result")
            result?.let { addItemToList(it) }
            d("newTitle", "${itemList.size}")
        }
    }

    private fun addItemToList(newItem: ItemDelivery) {
        itemList.add(newItem)
    }


    private fun sendDataToHomeFragment(): Boolean {
        val newLocation = binding.etNewLocation.text.toString()
        val icon = itemList[0].icon
        val title = itemList[0].title
        val id = itemList[0].id

        if (newLocation.isEmpty()) {
            Toast.makeText(context, getString(R.string.input_is_empty), Toast.LENGTH_SHORT).show()
            return false
        } else {
            val newItem = ItemDelivery(id = id, icon = icon, title = title, location = newLocation)
            parentFragmentManager.setFragmentResult(
                "editedLocation",
                Bundle().apply { putParcelable("editedLocation", newItem) })
            return true
        }
/*
        d("editedLocation", " $title")
        d("editedLocation", "$newLocation")*/

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}