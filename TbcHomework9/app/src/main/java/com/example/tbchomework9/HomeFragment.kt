package com.example.tbchomework9

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tbchomework9.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private  var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listeners()
    }

    private fun listeners(){
        binding!!.btnSignIn.setOnClickListener {
            addAnotherFragment(SignUpFragment(),"SignUpFragment")
        }
        binding!!.btnBack.setOnClickListener{
           requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun addAnotherFragment(fragment: Fragment,name:String) {
        requireActivity().supportFragmentManager.beginTransaction()    // adding new fragment.
            .replace(R.id.fragmentContainer, fragment).addToBackStack(name).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}