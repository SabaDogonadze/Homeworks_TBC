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
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserRegisterRequest
import com.example.tbchomework18.databinding.FragmentRegisterBinding
import com.example.tbchomework18.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.jar.Pack200

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
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val repeatPassword = binding.etRepeatPassword.text.toString().trim()

            if (viewModel.validateViewInputs(email, password, repeatPassword)) {
                val serverRequest = UserRegisterRequest(email, password)
                viewModel.userRegister(serverRequest)
            }
        }
        binding.btnBack.setOnClickListener {
            goToLogInFragment()
        }
    }

    private fun goToLogInFragment() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLogInFragment())
    }


    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userRegisterResponseFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            Log.d("12345", "Email saved: $it")
                            val email = binding.etEmail.text.toString()
                            val password = binding.etPassword.text.toString()
                            sendDataToLogInFragment(email,password)
                            goToLogInFragment()
                        }

                        is Resource.Error -> {
                            Log.d("12345", "Email saved: $it")
                            Toast.makeText(
                                context,
                                "Login Failed, Please Check Inputs",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        null -> Pack200.Packer.PASS
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.viewsValidationState.collect{
                    it?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
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