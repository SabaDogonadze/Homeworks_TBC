package com.example.tbchomework18.viewmodel

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.common.Resource
import com.example.tbchomework18.data.HomePageServerResponse
import com.example.tbchomework18.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val _userDataResponseFlow = MutableStateFlow<Resource<HomePageServerResponse>?>(null)
    val userDataResponseFlow : StateFlow<Resource<HomePageServerResponse>?> = _userDataResponseFlow

    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            _userDataResponseFlow.value = Resource.Loading(load = true)
            try {
                val userServerResponse = Network.usersService().getUsersData()
                d("homeViewModel","${userServerResponse}")
                if(userServerResponse.isSuccessful){
                    _userDataResponseFlow.value = Resource.Success(dataSuccess = userServerResponse.body())
                }else{
                    _userDataResponseFlow.value = Resource.Error(errorMessage = userServerResponse.errorBody()?.string()?:"")
                }
            }catch (e:Exception){
                //
            }
        }
    }

}