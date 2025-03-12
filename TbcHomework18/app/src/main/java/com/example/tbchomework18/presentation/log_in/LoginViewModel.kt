package com.example.tbchomework18.presentation.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserLogInRequest
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.log_in.LogInResponse
import com.example.tbchomework18.domain.usecase.validation.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInRepository: LogInRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    private val _userLogInResponseFlow = MutableStateFlow<Resource<LogInResponse>?>(null)
    val userLoginResponseFlow: StateFlow<Resource<LogInResponse>?> = _userLogInResponseFlow

    private val _uiEvents = Channel<LogInEvents>()
    val uiEvents get() = _uiEvents.receiveAsFlow()


    fun saveEmailAndUserSession(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!validateEmailUseCase(email)){
                _uiEvents.send(LogInEvents.ActivateLogInButton(false))
                return@launch
            }
            dataStoreRepository.saveEmailAndSession(email)
        }

    }

    fun getUserResponse(userLoginRequest: UserLogInRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = logInRepository.logIn(userLoginRequest).collect {
                when (it) {
                    is Resource.Loading -> {
                        _userLogInResponseFlow.value = Resource.Loading(it.loading)
                    }

                    is Resource.Success -> {
                        _userLogInResponseFlow.value =
                            Resource.Success(dataSuccess = it.dataSuccess!!)
                    }

                    is Resource.Error -> {
                        _userLogInResponseFlow.value = Resource.Error(it.errorMessage)
                    }
                }
            }
        }
    }

   /* fun validateViewInputs(email: String, password: String): Boolean {   // should use use cases
        if (email.isEmpty()) {
            _viewsValidationState.value = "Email Is Empty. Please Write Correct Email"
            return false
        }
        if (password.isEmpty()) {
            _viewsValidationState.value = "Password Is Empty. Please Write Correct Password"
            return false
        }
        return true
    }*/
}