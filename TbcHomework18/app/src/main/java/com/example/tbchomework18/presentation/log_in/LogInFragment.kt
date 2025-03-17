package com.example.tbchomework18.presentation.log_in

import android.util.Log
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.databinding.FragmentLogInBinding
import com.example.tbchomework18.presentation.base.BaseFragment
import com.example.tbchomework18.util.extensions.launchObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun setUp() {
        retrieveDataFromRegisterFragment()
        bindObservers()
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnLogIn.setOnClickListener {
            d("kkllkk", "shemovida fragmentshi")
            viewModel.event(
                LogInEvent.LoginButtonClicked(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString(),
                    binding.checkbox.isChecked // check states
                )
            )
        }
        binding.btnRegister.setOnClickListener {
            openRegisterFragment()
        }
    }

    private fun openHomeFragment() {
        findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment())
        Log.d("12345", "openHomeFragment")
    }

    private fun openRegisterFragment() {
        findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegisterFragment())
    }

    private fun bindObservers() {
        launchObserver {
            viewModel.state.collect { state ->
                d("kkllkk", state.toString())
                // binding.btnLogIn.isEnabled = state.isValidEmail && state.isValidPassword
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                state.errorMessage?.let { message ->
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        launchObserver {
            viewModel.uiEvents.collect { event ->
                when (event) {
                    is OneTimeLoginInEvents.NavigateToHome -> openHomeFragment()
                    is OneTimeLoginInEvents.ShowError -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    /*  private fun sendDataToProfileFragment(email:String){
          parentFragmentManager.setFragmentResult(
              "SuccessfullEmail",
              Bundle().apply {
                  putString("emailKey", email)
              }
          )
      }*/

    private fun retrieveDataFromRegisterFragment() {
        parentFragmentManager.setFragmentResultListener(
            "requestKey",
            viewLifecycleOwner
        ) { _, bundle ->

            val email = bundle.getString("emailKey")
            val password = bundle.getString("passwordKey")

            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
        }

    }


}