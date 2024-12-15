package com.example.tbchomework7

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tbchomework7.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    private lateinit var fireBaseAuth: FirebaseAuth

    /* private lateinit var auth: FirebaseAuth*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*  auth = Firebase.auth*/
        setUp()
    }

    private fun setUp() {
        listeners()
    }

    private fun listeners() {
        binding.btnBack.setOnClickListener {
            val intent = intent  // creating a local variable of the same intent which was passed from previous activity , NOT BEST PRACTICE
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.btnNext.setOnClickListener {
            registerUser()
        }
    }

    private fun launchAnotherActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        result.launch(intent)
    }

    private fun registerUser() {
        fireBaseAuth = FirebaseAuth.getInstance()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (EmailValidation().emailValidation(
                    email,
                    binding
                ) && UserInputValidations().passwordLength(
                    password,
                    binding
                ) && UserInputValidations().passwordIsCorrect(password, binding)
            ) {
                fireBaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            launchAnotherActivity(SignUpActivity::class.java)
                        } else {
                            d("12345", "${it.exception}")
                            Toast.makeText(
                                this,
                                getString(R.string.registration_was_unsuccessful),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
               /* Toast.makeText(this, "Input Valid Email", Toast.LENGTH_SHORT).show()*/
            }
        }else{
            Toast.makeText(this,
                getString(R.string.some_of_the_fields_are_empty), Toast.LENGTH_SHORT).show()
        }
    }
}