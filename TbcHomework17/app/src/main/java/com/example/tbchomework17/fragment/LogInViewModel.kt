package com.example.tbchomework17.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework17.model.UserLoginRequest
import com.example.tbchomework17.model.UserLoginResponse
import com.example.tbchomework17.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LogInViewModel:ViewModel() {
    private val _userLoginResponse = MutableStateFlow<Response<UserLoginResponse>?>(null)
    val userLoginResponse = _userLoginResponse.asStateFlow()

    fun loginUser(userLoginRequest: UserLoginRequest){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val serverResponse = Network.networkApiService().login(userLoginRequest)
                _userLoginResponse.value = serverResponse
                Log.d("12345", "$serverResponse")
            } catch (e:Exception){ // catches recoverable issues maybe better use other methods, like Throwable which is base class for all errorrs and exceptions
                Log.d("12345", e.toString()) // but exception is better because it catches recoverable errors and throwable does not like stackoverflow
                _userLoginResponse.value = null
                Log.d("12345", _userLoginResponse.toString())
            }

        }
    }
}