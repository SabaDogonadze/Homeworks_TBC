package com.example.tbchomework24.presentation.fragment.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework24.databinding.FragmentHomeBinding
import com.example.tbchomework24.presentation.fragment.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewmodel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeRecyclerAdapter

    override fun setUp() {
        setUpRecycler()
        observers()
        homeViewmodel.getStory()
        homeViewmodel.getPost()
    }
    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewmodel.homeItemsFlow.collect { homeItems ->
                    homeAdapter.submitList(homeItems)
                }
            }
        }
    }
    private fun setUpRecycler() {
        homeAdapter = HomeRecyclerAdapter()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = homeAdapter
        }
    }
}