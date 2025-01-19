package com.example.tbchomework17.fragment

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework17.model.UserRegisterRequest
import com.example.tbchomework17.model.UserRegisterResponse
import com.example.tbchomework17.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterViewModel:ViewModel() {
    private val _userRegisterResponse = MutableStateFlow<Response<UserRegisterResponse>?>(null)
    val userRegisterResponse = _userRegisterResponse.asStateFlow()

    fun registerUser(userRegisterRequest: UserRegisterRequest){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val serverResponse = Network.networkApiService().register(userRegisterRequest)
                _userRegisterResponse.value = serverResponse
            }catch (e:Exception){ // catches recoverable issues maybe better use other methods, like Throwable which is base class for all errorrs and exceptions
                d("12345", e.toString())  // but exception is better because it catches recoverable errors and throwable does not like stackoverflow
                _userRegisterResponse.value = null
                d("12345", userRegisterRequest.toString())
            }
        }
    }
}