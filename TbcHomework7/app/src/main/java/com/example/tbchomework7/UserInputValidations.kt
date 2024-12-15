package com.example.tbchomework7

import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.viewbinding.ViewBinding

class UserInputValidations {
    fun <T : ViewBinding> passwordLength(password: String, binding: T) :Boolean{
        var passwordIsGood = true
        if(password.length < 5){
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.password_is_too_short_minimum_input_5),Toast.LENGTH_LONG).show()
            passwordIsGood = false
        }
        return passwordIsGood
    }

    fun <T : ViewBinding> passwordIsCorrect(password: String, binding: T) :Boolean{
        var correctPassword = true
        if(password.isDigitsOnly()){
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.password_must_contain_minimum_1_symbol),Toast.LENGTH_LONG).show()
            correctPassword = false
        }
        return correctPassword
    }
}