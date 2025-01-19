package com.example.tbchomework17.fragment

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tbchomework17.R
import com.example.tbchomework17.databinding.FragmentRegisterBinding
import com.example.tbchomework17.model.UserRegisterRequest
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    private val viewModel: RegisterViewModel by viewModels ()
    override fun setUp() {
        clickListeners()
        collectUserRegisterInfo()
    }

    override fun clickListeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                val email = edEmail.text.toString()
                val userName = edUserName.text.toString()
                val password = edPassword.text.toString()

                if(checkInputFields(userName,email,password)){
                    val userRegisterRequest = UserRegisterRequest(email,password)
                    viewModel.registerUser(userRegisterRequest)
                }
            }
        }
    }

    private fun collectUserRegisterInfo(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userRegisterResponse.collect{
                    it?.let{
                        if(it.isSuccessful){       // Errors should be handled on ViewModel side , But for now i write this here , ( we should make a sealed class where errors and states are handled)
                            Toast.makeText(context,
                                getString(R.string.registration_is_successful),Toast.LENGTH_LONG).show()
                            Log.d("12345", "Registration Successful")
                        }else{
                            Toast.makeText(context,
                                getString(R.string.registration_is_not_successful),Toast.LENGTH_LONG).show()
                            Log.d("12345", "Registration Failed")
                        }
                    }
                }
            }
        }
    }

    private fun checkInputFields( userName:String,  email:String,  password:String ):Boolean{
        if(email != "eve.holt@reqres.in"){
            Toast.makeText(context,
                getString(R.string.email_is_incorrect_please_try_again),Toast.LENGTH_LONG).show()
            return false
        }
        if(userName.isEmpty()){
            Toast.makeText(context,
                getString(R.string.username_is_incorrect_please_try_again),Toast.LENGTH_LONG).show()
            return false
        }
        if(password.isEmpty()){
            Toast.makeText(context,
                getString(R.string.password_is_empty_please_write_something),Toast.LENGTH_LONG).show()
            return false
        }
        return true

    }

}