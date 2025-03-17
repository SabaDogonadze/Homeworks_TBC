package com.example.tbchomework18.presentation.profile

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.databinding.FragmentProfileBinding
import com.example.tbchomework18.presentation.base.BaseFragment
import com.example.tbchomework18.util.extensions.launchObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val profileViewModel: ProfileViewModel by viewModels()
    override fun setUp() {
        bindObservers()
        clickListeners()
        getDataFromLoginFragment()
    }

    override fun clickListeners() {
        binding.btnLogOut.setOnClickListener {
            profileViewModel.event(ProfileEvent.LogOutButtonClicked)
        }
    }

    private fun bindObservers() {
       launchObserver {
           profileViewModel.state.collect { state ->
               d("mmnnmm","$state")
               binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
               state.errorMessage?.let { message ->
                   Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
               }
               state.email?.let { email ->
                   binding.tvEmail.text = email
               }
           }
       }

       launchObserver {
           profileViewModel.uiEvents.collect { event ->
               when (event) {
                   is OneTimeProfileEvents.NavigateToLogIn -> openLogInFragment()
                   is OneTimeProfileEvents.ShowError -> Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
               }
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
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLogInFragment())
    }
}