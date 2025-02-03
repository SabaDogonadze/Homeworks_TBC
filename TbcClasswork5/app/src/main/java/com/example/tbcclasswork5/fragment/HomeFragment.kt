package com.example.tbcclasswork5.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbcclasswork5.R
import com.example.tbcclasswork5.adapter.HomeRecyclerAdapter
import com.example.tbcclasswork5.common.Resource
import com.example.tbcclasswork5.databinding.FragmentHomeBinding
import com.example.tbcclasswork5.room.UserDataBase
import com.example.tbcclasswork5.room.UserRepository
import com.example.tbcclasswork5.viewmodel.UserViewmodel
import com.example.tbcclasswork5.room.toApiUser
import com.example.tbcclasswork5.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import java.util.jar.Pack200.Packer.PASS

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private lateinit var homeViewModel: UserViewmodel
    private lateinit var adapter: HomeRecyclerAdapter

    override fun setUp() {
        setUpRepository()
        setUpRecycler()
        if(checkInternetConnection(requireContext())){
            clickListeners()
        }else{
            saveDataToRoom()
            Toast.makeText(requireContext(),
                getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
        }
    }

    override fun clickListeners() {
        binding.btnGetData.setOnClickListener {
            observers()
            saveDataToRoom()
        }
    }

    private fun observers(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                homeViewModel.userDataResponseFlow.collect{
                    when(it){
                        is Resource.Success ->{
                            binding.progressBar.visibility = View.GONE
                            it.data?.let { serverResponse ->
                                Log.d("homeFragment", "Users: ${serverResponse.users}")
                                adapter.submitList(serverResponse.users)
                            }
                        }
                        is Resource.Error ->{
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                        }
                        is Resource.Loading ->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        null -> PASS
                    }
                }
            }
        }
    }

    private fun saveDataToRoom() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.allUsers.collect { roomUsers ->
                    val apiUsers = roomUsers.map { it.toApiUser() }
                    adapter.submitList(apiUsers)
                }
            }
        }
    }

    private fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    private fun setUpRecycler(){
        adapter = HomeRecyclerAdapter()
        binding.apply {
            recyclerView.adapter = adapter
           recyclerView.layoutManager  = LinearLayoutManager(context)
        }
    }

    private fun setUpRepository(){
        val userDao = UserDataBase.getDatabase(requireContext()).userDao()
        val permissionDao = UserDataBase.getDatabase(requireContext()).permissionDao()
        val userPermissionDao = UserDataBase.getDatabase(requireContext()).userPermissionDao()
        val repository = UserRepository(userDao, permissionDao, userPermissionDao)
        homeViewModel = ViewModelProvider(this, UserViewModelFactory(repository)).get(UserViewmodel::class.java)
        homeViewModel.getUserData()
    }

}