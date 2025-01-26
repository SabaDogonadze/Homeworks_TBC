package com.example.tbchomework18.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.common.Resource
import com.example.tbchomework18.data.UserRegisterRequest
import com.example.tbchomework18.data.UserRegisterResponse
import com.example.tbchomework18.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel:ViewModel() {
    private val _userRegisterResponseFlow = MutableStateFlow<Resource<UserRegisterResponse>?>(null)
    val userRegisterResponseFlow : StateFlow<Resource<UserRegisterResponse>?> = _userRegisterResponseFlow

    private val _viewsValidationState = MutableStateFlow<String?>(null)
    val viewsValidationState : StateFlow<String?> = _viewsValidationState

    fun userRegister(userRegisterRequest: UserRegisterRequest){
        viewModelScope.launch(Dispatchers.IO) {
            _userRegisterResponseFlow.value = Resource.Loading(true)
            try {
                val userRegisterServerResponse = Network.networkService().register(userRegisterRequest)
                if(userRegisterServerResponse.isSuccessful){
                    _userRegisterResponseFlow.value = Resource.Success(dataSuccess = userRegisterServerResponse.body())
                    Log.d("12345", "Email saved: $userRegisterServerResponse")
                }else{
                    Log.d("12345", "ar shemodis: $userRegisterServerResponse")
                    _userRegisterResponseFlow.value = Resource.Error(errorMessage = userRegisterServerResponse.errorBody()?.string()?:"")
                }
            }catch (e:Exception){
                _userRegisterResponseFlow.value = Resource.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun validateViewInputs(email:String, password:String,repeatPassword:String):Boolean{
        if(email.isEmpty()){
            _viewsValidationState.value = "Email Is Empty. Please Write Correct Email"   // how can i get a context in viewmodel
            return false
        }
        if(password.isEmpty()){
            _viewsValidationState.value = "Password Is Empty. Please Write Correct Password"
            return false
        }
        if(repeatPassword.isEmpty()){
            _viewsValidationState.value = "Repeated Password Is Empty. Please Write Correct Password"
            return false
        }
        if(repeatPassword != password){
            _viewsValidationState.value = "Repeated Password Is Not Match To A Inputed Password"
            return false
        }
        return true
    }

}