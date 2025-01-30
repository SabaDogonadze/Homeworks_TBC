package com.example.tbchomework21.fragment

import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbchomework21.databinding.FragmentHomeBinding
import com.example.tbchomework21.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun setUp() {
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnSave.setOnClickListener {
            homeViewModel.apply {
                saveUserName(binding.etUserFirstName.text.toString())
                saveUserLastName(binding.etUerLastName.text.toString())
                saveUserEmail(binding.etUserEmail.text.toString())
            }
            showSnackbar(binding.root,"Data Saved")
        }

        binding.btnRead.setOnClickListener {
            observers()
            visibilityChanger()
        }
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            d("ragacaa" ,"${Thread.currentThread().name}")
            homeViewModel.getSavedUserName(requireActivity().application)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userName.collect {
                   binding.tvUserFirstName.text = it
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getSavedUserLastName(requireActivity().application)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userLastName.collect {
                    binding.tvUserLastName.text = it
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getSavedUserEmail(requireActivity().application)
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userEmail.collect {
                    binding.tvUserEmail.text = it
                }
            }
        }
    }
    private fun visibilityChanger() {
        binding.apply {
            tvUserLastName.visibility = View.VISIBLE
            tvUserEmail.visibility = View.VISIBLE
            tvUserFirstName.visibility = View.VISIBLE
        }
    }
    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}