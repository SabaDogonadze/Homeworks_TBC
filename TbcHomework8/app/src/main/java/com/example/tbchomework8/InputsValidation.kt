package com.example.tbchomework8

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.tbchomework8.databinding.ActivityMainBinding

class InputsValidation(private val binding: ActivityMainBinding) {
    private val errorMessage = binding.root.context.getString(R.string.error)
    private val successMessage = binding.root.context.getString(R.string.success)
    private val errorColor = ContextCompat.getColor(binding.root.context, R.color.red)
    private val successColor = ContextCompat.getColor(binding.root.context, R.color.green)

    private fun setStatus(textView: TextView, statusMessage: String, color: Int) {
        textView.text = statusMessage
        textView.setTextColor(color)
        textView.visibility = View.VISIBLE
    }

    fun isFirstNameValid(firstName: String): Boolean {
        var valid = true
        if (firstName.isEmpty()) {
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.firstname_is_empty), Toast.LENGTH_SHORT).show()
            setStatus(binding.tvFirstNameStatus, errorMessage, errorColor)
            valid = false
            return valid
        } else {
            setStatus(binding.tvFirstNameStatus, successMessage, successColor)
            return valid
        }
    }

    fun isLastNameValid(lastName: String): Boolean {
        var valid = true
        if (lastName.isEmpty()) {
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.lastname_is_empty), Toast.LENGTH_SHORT).show()
            setStatus(binding.tvLastNameStatus, errorMessage, errorColor)
            valid = false
            return valid
        } else {
            setStatus(binding.tvLastNameStatus, successMessage, successColor)
            return valid
        }
    }

    fun isAgeValid(age: String): Boolean {
        var valid = true
        if (age.isEmpty()) {
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.age_is_empty), Toast.LENGTH_SHORT).show()
            setStatus(binding.tvAgeStatus, errorMessage, errorColor)
            valid = false
            return valid
        } else {
            setStatus(binding.tvAgeStatus, successMessage, successColor)
            return valid
        }
    }

    fun isEmailValid(email: String): Boolean {
        var valid = true
        if (email.isEmpty()) {
            Toast.makeText(binding.root.context,
                binding.root.context.getString(R.string.email_is_empty), Toast.LENGTH_SHORT).show()
            setStatus(binding.tvEmailStatus, errorMessage, errorColor)
            valid = false
            return valid
        } else {
            setStatus(binding.tvEmailStatus, successMessage, successColor)
            return valid
        }
    }
}