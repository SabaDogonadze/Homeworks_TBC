package com.example.tbchomework17.fragment

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbchomework17.R
import com.example.tbchomework17.databinding.FragmentLoginInBinding
import com.example.tbchomework17.model.UserLoginRequest
import kotlinx.coroutines.launch


class LoginInFragment : BaseFragment<FragmentLoginInBinding>(FragmentLoginInBinding::inflate) {
    private val viewModel: LogInViewModel by viewModels()
    override fun setUp() {
        clickListeners()
        collectLoginUserInfo()
    }

    override fun clickListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                val userName = edUserName.text.toString()
                val password = edPassword.text.toString()

                if(checkInputFields(userName,password)){
                    val userLoginRequest = UserLoginRequest(userName,password)
                    viewModel.loginUser(userLoginRequest)
                }
            }
        }
    }

    private fun collectLoginUserInfo(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userLoginResponse.collect{
                    it?.let {
                        if (it.isSuccessful) {
                            // Errors should be handled on ViewModel side , But for now i write this here , ( we should make a sealed class where errors and states are handled)
                            Toast.makeText(context,
                                getString(R.string.login_is_successful), Toast.LENGTH_LONG).show()
                            Log.d("12345", "Login Successful")
                        } else {
                            Toast.makeText(context,
                                getString(R.string.login_is_not_successful), Toast.LENGTH_LONG).show()
                            Log.d("12345", "Login Failed")
                        }
                    }
                }
            }
        }
    }

    private fun checkInputFields( userName:String,  password:String ):Boolean{

        if(userName.isEmpty()){
            Toast.makeText(context,
                getString(R.string.username_is_incorrect_please_try_again), Toast.LENGTH_LONG).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(context,
                getString(R.string.password_is_empty_please_write_something), Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }



}