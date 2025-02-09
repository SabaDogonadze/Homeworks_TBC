package com.example.tbchomework18.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserRegisterRequest
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.domain.register.RegisterResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    ViewModel() {
    private val _userRegisterResponseFlow = MutableStateFlow<Resource<RegisterResponse>?>(null)
    val userRegisterResponseFlow: StateFlow<Resource<RegisterResponse>?> =
        _userRegisterResponseFlow

    private val _viewsValidationState = MutableStateFlow<String?>(null)
    val viewsValidationState: StateFlow<String?> = _viewsValidationState

    fun userRegister(userRegisterRequest: UserRegisterRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = registerRepository.register(userRegisterRequest).collect {
                when (it) {
                    is Resource.Loading -> {_userRegisterResponseFlow.value = Resource.Loading(it.loading)}
                    is Resource.Success -> {_userRegisterResponseFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)}
                    is Resource.Error -> {_userRegisterResponseFlow.value = Resource.Error(it.errorMessage)}
                }
            }
        }
    }

    fun validateViewInputs(email: String, password: String, repeatPassword: String): Boolean {
        if (email.isEmpty()) {
            _viewsValidationState.value =
                "Email Is Empty. Please Write Correct Email"   // how can i get a context in viewmodel
            return false
        }
        if (password.isEmpty()) {
            _viewsValidationState.value = "Password Is Empty. Please Write Correct Password"
            return false
        }
        if (repeatPassword.isEmpty()) {
            _viewsValidationState.value =
                "Repeated Password Is Empty. Please Write Correct Password"
            return false
        }
        if (repeatPassword != password) {
            _viewsValidationState.value = "Repeated Password Is Not Match To A Inputed Password"
            return false
        }
        return true
    }

}