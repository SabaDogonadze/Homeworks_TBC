package com.example.tbchomework18.fragment

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.databinding.FragmentProfileBinding
import com.example.tbchomework18.datastore.DataStoreUtil
import com.example.tbchomework18.viewmodel.ProfileViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val profileViewModel: ProfileViewModel by viewModels()
    override fun setUp() {
        Log.d("12345", "HomeFragmentOpened")
        getEmailFromDataStore()
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnLogOut.setOnClickListener {
            lifecycleScope.launch {
                profileViewModel.clearSession()
                binding.progressBar.visibility = View.VISIBLE
                delay(3000L)
                binding.progressBar.visibility = View.GONE
                openLogInFragment()
            }
        }
    }

    private fun getEmailFromDataStore(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                DataStoreUtil.readEmail().collect{
                    binding.tvEmail.text = it
                    Log.d("12345", "Email read from DataStore: $it")
                }
            }
        }
    }

    private fun openLogInFragment() {
        findNavController().navigate(ProfileFragmentDirections.actionHomeFragmentToLogInFragment())
    }
}



/* private fun getDataFromLoginFragment() {
       parentFragmentManager.setFragmentResultListener(
           "SuccessfullEmail",
           viewLifecycleOwner
       ) { _, bundle ->
           val email = bundle.getString("emailKey")
           binding.tvEmail.text = email
       }
   }*/