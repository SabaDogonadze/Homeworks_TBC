package com.example.tbcclasswork3.fragment

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tbcclasswork3.R
import com.example.tbcclasswork3.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
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
        binding.btnStart.setOnClickListener {
            openNewFragment()
        }
    }

    private fun openNewFragment() {
        val number = binding.etInput.text.toString().toInt()
        if (number in 3..5){
            sendDataToGameFragment()
        }else{
            Toast.makeText(context,
                getString(R.string.input_number_between_3_to_5), Toast.LENGTH_SHORT).show()
        }
       /* findNavController().navigate(destination)*/
    }


    private fun sendDataToGameFragment() {
        val number = binding.etInput.text.toString().toInt()
        d("q12345","$number")
        val bundle = Bundle()
        bundle.putInt("number",number)
        val destination = MainFragmentDirections.actionMainFragmentToGameFragment(number)
        findNavController().navigate(destination)

        /*parentFragmentManager.setFragmentResult(      // this was asynchronous and numberResult was not getting value fast enough on GameFragment
            "number",
            Bundle().apply { putInt("number", number) })*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}