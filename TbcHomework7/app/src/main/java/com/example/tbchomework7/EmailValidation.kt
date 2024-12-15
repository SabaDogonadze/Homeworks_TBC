package com.example.tbchomework7

import android.util.Patterns
import android.widget.Toast
import androidx.viewbinding.ViewBinding

class EmailValidation {
    fun <T:ViewBinding>emailValidation(email:String,binding: T):Boolean{
        var isEmailCorrect = true
        if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.input_valid_email), Toast.LENGTH_SHORT).show()
            isEmailCorrect = false
        }
        return isEmailCorrect
    }
}