package com.example.tbchomework111

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tbchomework111.databinding.FragmentAddNewDestinationBinding


class AddNewDestinationFragment : Fragment() {

    private var _binding: FragmentAddNewDestinationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewDestinationBinding.inflate(layoutInflater, container, false)
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
            if( sendDataToHomeFragment()){
            sendDataToHomeFragment()
            findNavController().popBackStack()
        }}
    }

    private fun sendDataToHomeFragment():Boolean {
        val destinationName = binding.etDestinationName.text.toString()
        val location = binding.etLocation.text.toString()
        val icon = R.drawable.ic_home

        if(destinationName.isEmpty() || location.isEmpty()){
            Toast.makeText(context,getString(R.string.input_is_empty),Toast.LENGTH_SHORT).show()
            return false
        }else{
            val newItem = ItemDelivery(icon = icon, title = destinationName, location = location)
            parentFragmentManager.setFragmentResult(
                "newLocation",  // sending data to another fragment, first key is for fragmentResult itself
                Bundle().apply { putParcelable("newLocation", newItem) }) // second key is for bundle to get an Item Afterwards
            return true
        }

        /*d("newLocation", " $destinationName")
        d("newLocation", "$location")*/

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}