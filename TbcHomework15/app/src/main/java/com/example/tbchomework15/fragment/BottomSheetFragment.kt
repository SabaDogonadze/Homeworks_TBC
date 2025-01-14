package com.example.tbchomework15.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tbchomework15.data.CardData
import com.example.tbchomework15.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val args: BottomSheetFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners(){
        binding.btnYes.setOnClickListener {
            deleteSelectedCard(args.id)
            navigateToMainFragment()
        }
        binding.btnNo.setOnClickListener {
            navigateToMainFragment()
        }
    }

    private fun deleteSelectedCard(id: String){
        CardData.cardData.removeIf{it.id == id}
    }

    private fun navigateToMainFragment(){
        findNavController().navigate(BottomSheetFragmentDirections.actionBottomSheetFragmentToItemFragment())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}