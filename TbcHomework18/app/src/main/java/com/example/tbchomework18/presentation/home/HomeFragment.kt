package com.example.tbchomework18.presentation.home

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.databinding.FragmentHomeBinding
import com.example.tbchomework18.data.paging.HomeRecyclerAdapter
import com.example.tbchomework18.data.paging.LocationLoadingStateAdapter
import com.example.tbchomework18.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.jar.Pack200

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeRecyclerAdapter

    override fun setUp() {
        /*homeViewModel.getUserData()*/
        setUpHomeRecycler()
        setUpRecycler()
        bindObservers()
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnProfile.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }

    private fun setUpRecycler() {
        adapter = HomeRecyclerAdapter()
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter
        }
    }


    private fun bindObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.userDataResponseFlow.collect {
                    when (it) {
                        is Resource.Success -> {
                            binding.loader.visibility = View.GONE
                            Log.d("homeViewModel", "succsess")
                            Log.d(
                                "homeViewModel",
                                "${homeViewModel.userDataResponseFlow.value?.data?.data}"
                            )
                            it.data?.data?.let { data ->
                                /* adapter.submitList(data)*/
                            }
                        }

                        is Resource.Error -> {
                            Toast.makeText(context, "Some Error", Toast.LENGTH_SHORT).show()
                            binding.loader.visibility = View.GONE
                            Log.d("homeViewModel", "error")
                        }

                        is Resource.Loading -> {
                            Log.d("homeViewModel", "loader")
                            binding.loader.visibility = View.VISIBLE
                        }

                        null -> Pack200.Packer.PASS
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.usersFlow.collect { pagingData ->
                    adapter.submitData(pagingData)
                }
            }

            viewLifecycleOwner.lifecycleScope.launch {
                adapter.loadStateFlow.collect { loadState ->

                    binding.apply {
                        loader.isVisible = loadState.refresh is LoadState.Loading
                        btnRetry.isVisible = loadState.refresh is LoadState.Error
                        btnRetry.setOnClickListener { adapter.retry() }
                    }

                    if (loadState.refresh is LoadState.Error) {
                        val error = (loadState.refresh as LoadState.Error).error
                        Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    private fun setUpHomeRecycler() {
        adapter = HomeRecyclerAdapter()

        val adapterWithLoadState = adapter.withLoadStateHeaderAndFooter(
            header = LocationLoadingStateAdapter(adapter::retry),
            footer = LocationLoadingStateAdapter(adapter::retry)
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterWithLoadState
        }
    }
}