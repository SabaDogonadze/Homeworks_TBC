package com.example.tbchomework7

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tbchomework7.databinding.ActivitySiginUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySiginUpBinding
    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySiginUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp(){
        listeners()
    }

    private fun listeners(){
        binding.btnBack.setOnClickListener {
            val intent = intent  // creating a local variable of the same intent which was passed from previous activity , NOT BEST PRACTICE
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
        binding.btnSignUp.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            if(userName.isNotEmpty()){
                launchAnotherActivity(LogInActivity::class.java)
            }else{
                Toast.makeText(this, getString(R.string.field_is_empty),Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun launchAnotherActivity(activity:Class<*>){
        val intent = Intent(this,activity)
        result.launch(intent)
    }
}