package com.example.tbchomework18.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.common.Resource
import com.example.tbchomework18.data.UserLogInRequest
import com.example.tbchomework18.databinding.FragmentLogInBinding
import com.example.tbchomework18.viewmodel.LoginViewModel
import kotlinx.coroutines.launch
import java.util.jar.Pack200.Packer.PASS


class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun setUp() {
        Log.d("12345", "Log in Started")
        retrieveDataFromRegisterFragment()
        bindObservers()
        clickListeners()
    }

    override fun clickListeners() {
      binding.btnLogIn.setOnClickListener {
          val email = binding.etEmail.text.toString().trim()
          val password = binding.etPassword.text.toString().trim()

          if (binding.checkbox.isChecked) {
              viewModel.saveEmailAndUserSession(email)
              Log.d("snv", "Email saved: $email")
          }

            if(viewModel.validateViewInputs(email,password)){
                val serverLoginRequest  = UserLogInRequest(email,password)
                viewModel.getUserResponse(serverLoginRequest)
            }
      }
       binding.btnRegister.setOnClickListener {
           openRegisterFragment()
       }
    }

    private fun openHomeFragment(){
        findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToHomeFragment())
        Log.d("12345", "openHomeFragment")
    }
    private fun openRegisterFragment(){
        findNavController().navigate(LogInFragmentDirections.actionLogInFragmentToRegisterFragment())
    }

    private fun bindObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userLoginResponseFlow.collect{
                    when(it){
                        is Resource.Success -> {
                            viewModel.saveEmailAndUserSession(binding.etEmail.text.toString())
                            Log.d("12345", "ragaca: $it")
                            sendDataToProfileFragment(binding.etEmail.text.toString())
                            openHomeFragment()
                        }
                        is Resource.Error ->  {
                            Toast.makeText(context, "Login Failed,Please Check Inputs", Toast.LENGTH_LONG).show()
                        }
                        is Resource.Loading ->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        null -> PASS
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

    private fun sendDataToProfileFragment(email:String){
        parentFragmentManager.setFragmentResult(
            "SuccessfullEmail",
            Bundle().apply {
                putString("emailKey", email)
            }
        )
    }

    private fun retrieveDataFromRegisterFragment(){
        parentFragmentManager.setFragmentResultListener("requestKey", viewLifecycleOwner) { _, bundle ->

            val email = bundle.getString("emailKey")
            val password = bundle.getString("passwordKey")

            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
        }

    }


}