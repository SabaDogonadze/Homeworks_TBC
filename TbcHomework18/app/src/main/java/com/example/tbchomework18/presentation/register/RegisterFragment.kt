package com.example.tbchomework18.presentation.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.databinding.FragmentRegisterBinding
import com.example.tbchomework18.presentation.base.BaseFragment
import com.example.tbchomework18.util.extensions.launchObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels()
    override fun setUp() {
        Log.d("12345", "Register in Started")
        bindObservers()
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnRegister.setOnClickListener {
            viewModel.event(
                RegisterEvent.RegisterButtonClicked(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.etRepeatPassword.text.toString()
                )
            )

        }
        binding.btnBack.setOnClickListener {
            viewModel.event(RegisterEvent.BackButtonClicked)
        }
    }

    private fun goToLogInFragment() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLogInFragment())
    }


    private fun bindObservers() {
        launchObserver {
            viewModel.state.collect { state ->
                // binding.btnRegister.isEnabled = state.isValidEmail && state.isValidPassword
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                state.errorMessage?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        launchObserver {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    is OneTimeRegisterEvents.NavigateToLogIn -> {
                        sendDataToLogInFragment(
                            binding.etEmail.text.toString(),
                            binding.etPassword.text.toString()
                        )
                        goToLogInFragment()
                    }

                    is OneTimeRegisterEvents.ShowError -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun sendDataToLogInFragment(email: String, password: String) {
        parentFragmentManager.setFragmentResult(
            "requestKey",
            Bundle().apply {
                putString("emailKey", email)
                putString("passwordKey", password)
            }
        )

    }
}