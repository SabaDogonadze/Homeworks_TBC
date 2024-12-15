package com.example.tbchomework7

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tbchomework7.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private var result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        }
    private lateinit var fireBaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.btnLogIn.setOnClickListener {
            checkUser()
        }
    }

    private fun launchAnotherActivity(activity: Class<*>) {
        val intent = Intent(this, activity)
        result.launch(intent)
    }

    private fun checkUser() {
        fireBaseAuth = FirebaseAuth.getInstance()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            if (EmailValidation().emailValidation(email, binding)) {
                fireBaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        launchAnotherActivity(MainActivity::class.java)
                    } else {
                        Toast.makeText(
                            this,
                            getString(R.string.login_was_unsuccessful), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }else{
            Toast.makeText(this, getString(R.string.fill_empty_fields), Toast.LENGTH_SHORT)
                .show()
        }
    }
}