package com.example.tbchomework18.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.common.Resource
import com.example.tbchomework18.data.UserLogInRequest
import com.example.tbchomework18.data.UserLogInResponse
import com.example.tbchomework18.datastore.DataStoreUtil
import com.example.tbchomework18.network.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    private val _userLogInResponseFlow = MutableStateFlow<Resource<UserLogInResponse>?>(null)
    val userLoginResponseFlow: StateFlow<Resource<UserLogInResponse>?> = _userLogInResponseFlow

    private val _viewsValidationState = MutableStateFlow<String?>(null)
    val viewsValidationState : StateFlow<String?> = _viewsValidationState

    fun saveEmailAndUserSession(email:String){
        viewModelScope.launch {
            DataStoreUtil.saveEmailAndSession(email)
        }
    }

    fun getUserResponse(userLoginRequest: UserLogInRequest){
        viewModelScope.launch {
            _userLogInResponseFlow.value = Resource.Loading(true)
            try {
                val userLoginResponse = Network.networkService().logIn(userRequest = userLoginRequest)
                if (userLoginResponse.isSuccessful){
                    _userLogInResponseFlow.value = Resource.Success(dataSuccess = userLoginResponse.body())
                }else{
                    _userLogInResponseFlow.value = Resource.Error(errorMessage = userLoginResponse.errorBody()?.string()?:"")
                }
            }catch (e:Exception){
                // here we should implement custom errors
            }
        }
    }

   fun validateViewInputs(email:String, password:String):Boolean{
        if(email.isEmpty()){
            _viewsValidationState.value = "Email Is Empty. Please Write Correct Email"
            return false
        }
        if(password.isEmpty()){
            _viewsValidationState.value = "Password Is Empty. Please Write Correct Password"
            return false
        }
        return true
    }



}