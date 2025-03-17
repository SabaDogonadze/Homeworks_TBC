package com.example.tbchomework18.presentation.log_in

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.remote.register.UserLogInRequest
import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.domain.usecase.LogInUseCase
import com.example.tbchomework18.domain.usecase.validation.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginStateUi())
    val state = _state.asStateFlow()

    private val _uiEvents = Channel<OneTimeLoginInEvents>()
    val uiEvents get() = _uiEvents.receiveAsFlow()

    fun event(event: LogInEvent) {
        when(event) {
            is LogInEvent.LoginButtonClicked -> {
                val isEmailValid = validateEmailUseCase.invoke(event.email)
                d("kkllkk","${isEmailValid && _state.value.isValidPassword}")
                if (isEmailValid) {/* _state.value.isValidPassword*/
                    loginUser(UserLogInRequest(event.email,event.password),event.rememberMe)
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        _uiEvents.send(OneTimeLoginInEvents.ShowError("Invalid credentials"))
                    }
                }
            }
        }
    }


    private fun loginUser(userLoginRequest: UserLogInRequest,rememberMe:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            logInUseCase.invoke(userLoginRequest,rememberMe).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, isLoggedIn = true) }
                        _uiEvents.send(OneTimeLoginInEvents.NavigateToHome)
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, errorMessage = result.errorMessage) }
                        _uiEvents.send(OneTimeLoginInEvents.ShowError(result.errorMessage))
                    }
                }
            }
        }
    }

}