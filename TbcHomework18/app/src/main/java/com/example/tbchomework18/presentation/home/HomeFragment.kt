package com.example.tbchomework18.presentation.home

import android.util.Log.d
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework18.databinding.FragmentHomeBinding
import com.example.tbchomework18.presentation.base.BaseFragment
import com.example.tbchomework18.presentation.paging.LocationLoadingStateAdapter
import com.example.tbchomework18.util.extensions.launchObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeRecyclerAdapter

    override fun setUp() {
        setUpRecycler()
        bindObservers()
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnProfile.setOnClickListener {
            homeViewModel.event(HomeEvent.ProfileButtonClicked)
        }
    }

    private fun setUpRecycler() {
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


    private fun bindObservers() {
        launchObserver {
            homeViewModel.usersFlow.collect { pagingData ->
                adapter.submitData(pagingData)
            }
            launchObserver {
                homeViewModel.uiEvents.collect() { event ->
                    when (event) {
                        is OneTimeHomeEvents.NavigateToProfile -> {
                            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
                        }

                        is OneTimeHomeEvents.ShowError -> {
                            Toast.makeText(
                                requireContext(),
                                event.message,
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }

            launchObserver {
                homeViewModel.state.collect { state ->
                    binding.loader.isVisible = state.isLoading
                    state.errorMessage?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

    }
}