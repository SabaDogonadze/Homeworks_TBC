package com.example.tbchomework8

import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tbchomework8.databinding.ActivityMainBinding

class EmailValidation( private val binding: ActivityMainBinding) {

    private val errorMessage = binding.root.context.getString(R.string.error)
    private val successMessage = binding.root.context.getString(R.string.success)
    private val errorColor = ContextCompat.getColor(binding.root.context, R.color.red)
    private val successColor = ContextCompat.getColor(binding.root.context, R.color.green)

    private fun setStatus(textView: TextView, statusMessage: String, color: Int) {
        textView.text = statusMessage
        textView.setTextColor(color)
        textView.visibility = View.VISIBLE
    }
    fun emailValidation(email:String):Boolean{
        var isEmailCorrect = true
        if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.email_is_incorrect), Toast.LENGTH_SHORT).show()
            setStatus(binding.tvEmailStatus, errorMessage, errorColor)
            isEmailCorrect = false
            return isEmailCorrect
        }else{
            setStatus(binding.tvEmailStatus, successMessage, successColor)
            return isEmailCorrect
        }
    }
}