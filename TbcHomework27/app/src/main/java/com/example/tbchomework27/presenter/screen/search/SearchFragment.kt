package com.example.tbchomework27.presenter.screen.search

import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework27.databinding.FragmentSearchBinding
import com.example.tbchomework27.presenter.base.BaseFragment
import com.example.tbchomework27.presenter.event.SuggestionVehicleSearchEvent
import com.example.tbchomework27.presenter.event.SuggestionVehicleSearchOneTimeEvents
import com.example.tbchomework27.presenter.extensions.launchObserver
import com.example.tbchomework27.presenter.screen.adapter.SearchSuggestionRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var suggestionAdapter: SearchSuggestionRecyclerAdapter

    override fun setUp() {
        observeSearchQuery()
        observers()
        setUpRecycler()
    }

    private fun observers() {
        launchObserver {
            searchViewModel.state.collect { state ->
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                suggestionAdapter.submitList(state.vehicles)
            }
        }
        launchObserver {
            searchViewModel.uiEvents.collect { event ->
                when (event) {
                    is SuggestionVehicleSearchOneTimeEvents.ShowError -> Toast.makeText(
                        requireContext(),
                        event.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

    private fun setUpRecycler() {
        suggestionAdapter = SearchSuggestionRecyclerAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = suggestionAdapter
        }
    }

    private var searchJob: Job? = null

    private fun observeSearchQuery() {
        binding.etSearch.doAfterTextChanged { text ->
            searchJob?.cancel()
            searchJob = viewLifecycleOwner.lifecycleScope.launch {
                delay(3000)
                val query = text?.toString() ?: ""
                if (query.isNotBlank()) {
                    searchViewModel.event(SuggestionVehicleSearchEvent.SearchAndLoadVehicles(query))
                } else {
                    searchViewModel.event(SuggestionVehicleSearchEvent.ClearSearch)
                }
            }
        }
    }

}