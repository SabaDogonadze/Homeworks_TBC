package com.example.tbchomework7

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tbchomework7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp(){
        listeners()
    }
    private fun listeners(){
        binding.btnRegister.setOnClickListener {
            launchAnotherActivity(RegisterActivity::class.java)
        }
        binding.btnLogIn.setOnClickListener {
            launchAnotherActivity(LogInActivity::class.java)
        }
    }
    private fun launchAnotherActivity(activity:Class<*>){
        val intent = Intent(this,activity)
        result.launch(intent)
    }
}