package com.example.tbchomework18.fragment

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.databinding.FragmentSplashBinding
import com.example.tbchomework18.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun setUp() {
        bindObservers()
        viewModel.readSession()
    }

    override fun clickListeners() {

    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userSession.collect {
                    openFragments(it)
                }
            }
        }
    }

    private fun openFragments(session: Boolean) {
        if (session) {
            Log.d("12345", "$session")
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment2())
        } else {
            Log.d("12345", "$session")
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLogInFragment())
        }
    }

}