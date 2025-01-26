package com.example.tbchomework18.fragment

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbchomework18.common.Resource
import com.example.tbchomework18.databinding.FragmentHomeBinding
import com.example.tbchomework18.reyclerAdapter.UserDataRecyclerAdapter
import com.example.tbchomework18.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.util.jar.Pack200.Packer.PASS


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var adapter: UserDataRecyclerAdapter


    override fun setUp() {
            homeViewModel.getUserData()
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
        adapter = UserDataRecyclerAdapter()
        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter
        }
    }

    private fun bindObservers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.userDataResponseFlow.collect{
                   when(it){
                       is Resource.Success -> {
                           binding.loader.visibility = View.GONE
                           d("homeViewModel","succsess")
                           d("homeViewModel","${homeViewModel.userDataResponseFlow.value?.data?.data}")
                           it.data?.data?.let { data ->
                               adapter.submitList(data)
                           }
                       }
                       is Resource.Error -> {
                            Toast.makeText(context,"Some Error", Toast.LENGTH_SHORT).show()
                            binding.loader.visibility = View.GONE
                           d("homeViewModel","error")
                       }
                       is Resource.Loading -> {
                           d("homeViewModel","loader")
                            binding.loader.visibility = View.VISIBLE
                       }
                       null -> PASS
                   }
                }
            }
        }
    }

}