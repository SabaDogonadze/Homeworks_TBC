package com.example.tbcclasswork4.fragment

import android.util.Log.d
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcclasswork4.databinding.FragmentMainBinding
import kotlinx.coroutines.launch


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var adapter : MessageRecyclerView

    override fun setUp() {
        setUpRecycler()
        d("12345", "${viewModel.deserializedData}")
        clickListeners()
    }

    override fun clickListeners() {
        binding.btnSearch.setOnClickListener {
            setupSearchView()
        }
    }

    private fun setUpRecycler(){
        adapter = MessageRecyclerView()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
        submitMessageList()

    }

    private fun submitMessageList(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.userRegisterResponse.collect{
                    d("q12345", "shemovida")
                    d("q12345", "${viewModel.deserializedData}")
                  adapter.submitList(it)
                    d("q12345", "shemovida")
                    d("q12345", "${it}")
                }
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.filterMessage(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.filterMessage(it) }
                return true
            }
        })
    }

}