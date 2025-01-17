package com.example.tbchomework16.fragment

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework16.databinding.FragmentMainBinding


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var adapter: FragmentMainRecyclerAdapter
    override fun setUp() {
        setUpRecycler()
    }
    override fun clickListeners() {
        binding.btnRegister.setOnClickListener {
            adapter.setOnItemClickedParentRecyclerListener {
                val key = it.fieldId.toString()
                viewModel.keyValueList.add(key to it)
                d("Data in Main Fragment","${viewModel.keyValueList}")   // maybe click listener is faster and does not detect this operation
            }
        }
    }

    private fun setUpRecycler() {
        adapter = FragmentMainRecyclerAdapter()
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }
        adapter.submitList(viewModel.mappedListToResponse)

      /*  adapter.setOnItemClickedParentRecyclerListener { item ->
            val key = item.fieldId.toString()
            viewModel.keyValueList.add(key to item)            --> this way works
            d("MainFragment", "${viewModel.keyValueList}")
        }*/

        d("MainFragment", "${viewModel.mappedListToResponse}")
        d("MainFragment", "${viewModel.mappedListToResponse}")
    }


}