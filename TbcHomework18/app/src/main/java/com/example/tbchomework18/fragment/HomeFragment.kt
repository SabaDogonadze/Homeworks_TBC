package com.example.tbchomework18.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.databinding.FragmentHomeBinding
import com.example.tbchomework18.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
   /* private val homeViewModel: HomeViewModel by viewModels()*/
    private val splashViewModel: SplashViewModel by viewModels() // i think this is not a good practice ;Dd
    override fun setUp() {
        Log.d("12345", "HomeFragmentOpened")
        getDataFromLoginFragment()
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnLogOut.setOnClickListener {
            lifecycleScope.launch {
                splashViewModel.clearSession()
                delay(3000L)
                openLogInFragment()
            }
        }
    }

    private fun getDataFromLoginFragment() {
        parentFragmentManager.setFragmentResultListener(
            "SuccessfullEmail",
            viewLifecycleOwner
        ) { _, bundle ->
            val email = bundle.getString("emailKey")
            binding.tvEmail.text = email
        }
    }

    private fun openLogInFragment() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLogInFragment())
    }

}