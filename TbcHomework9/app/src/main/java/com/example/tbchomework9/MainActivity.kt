package com.example.tbchomework9

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tbchomework9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()    // customizing back button, if there is a fragment in backstack
                // fragment will be shown but if it is zero fragment in it, it calls finish and activity dies
                } else {
                    finish()
                }
            }
        })
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())   // opening new fragment
            .commit()
    }
}
