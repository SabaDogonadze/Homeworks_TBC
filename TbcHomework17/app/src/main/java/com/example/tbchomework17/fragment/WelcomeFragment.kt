package com.example.tbchomework17.fragment

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.tbchomework17.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    override fun setUp() {
        clickListeners()
    }

    override fun clickListeners() {
        setupNavigation(
            binding.btnRegister,
            WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment()
        )
        setupNavigation(
            binding.btnLogin,
            WelcomeFragmentDirections.actionWelcomeFragmentToLoginInFragment()
        )
    }

    private fun setupNavigation(button: View, action: NavDirections) {
        button.setOnClickListener {
            findNavController().navigate(action)
        }
    }
}