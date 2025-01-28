package com.example.tbchomework20.fragment

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework20.adapter.HomeRecyclerAdapter
import com.example.tbchomework20.adapter.LocationLoadingStateAdapter
import com.example.tbchomework20.databinding.FragmentHomeBinding
import com.example.tbchomework20.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: HomeRecyclerAdapter
    override fun setUp() {
        setUpHomeRecycler()
        observers()
    }

    override fun observers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.homeUserDataflow.collect { pagingData ->
                    adapter.submitData(pagingData)
                }
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
                    Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpHomeRecycler(){
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