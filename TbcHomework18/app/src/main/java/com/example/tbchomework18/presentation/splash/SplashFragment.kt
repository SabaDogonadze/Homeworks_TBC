package com.example.tbchomework18.presentation.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.tbchomework18.data.local.datastore.SessionTracker
import com.example.tbchomework18.databinding.FragmentSplashBinding
import com.example.tbchomework18.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(){ // i dont need base fragment here because it violates solid
    private val viewModel: SplashViewModel by viewModels()
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

     private fun setUp() {
        bindObservers()
        viewModel.readSession()
    }

    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                SessionTracker.userSession.collect {
                    delay(1000)
                    openFragments(it)
                }
            }
        }
    }

    private fun openFragments(session: Boolean) {
        if (session) {
            Log.d("12345", "$session")
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        } else {
            Log.d("12345", "$session")
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLogInFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}