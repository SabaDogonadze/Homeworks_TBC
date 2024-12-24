package com.example.tbchomework10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tbchomework10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFragment()
    }


    private fun openFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }
}