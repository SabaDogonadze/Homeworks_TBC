package com.example.tbchomework18.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.remote.register.UserRegisterRequest
import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.domain.usecase.RegisterUseCase
import com.example.tbchomework18.domain.usecase.validation.ValidateEmailUseCase
import com.example.tbchomework18.domain.usecase.validation.ValidateRegisterInputsUseCase
import com.example.tbchomework18.domain.utils.ValidateRegisterInputsUtils
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
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateRegisterInputsUseCase: ValidateRegisterInputsUseCase,
) :
    ViewModel(){

    private val _state = MutableStateFlow(RegisterStateUi())
    val state = _state.asStateFlow()

    private val _uiEvents = Channel<OneTimeRegisterEvents>()
    val uiEvents get() = _uiEvents.receiveAsFlow()

    fun event(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.RegisterButtonClicked -> {
                val validationResult = validateRegisterInputsUseCase.invoke(
                    event.email,
                    event.password,
                    event.repeatPassword
                )
                if (validationResult is ValidateRegisterInputsUtils.RegisterValidationResult.Success && validateEmailUseCase.invoke(event.email)) {
                    userRegister(UserRegisterRequest(event.email, event.password))
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        _uiEvents.send(OneTimeRegisterEvents.ShowError("Invalid credentials"))
                    }
                }
            }

            RegisterEvent.BackButtonClicked -> viewModelScope.launch(Dispatchers.IO) {
                _uiEvents.send(OneTimeRegisterEvents.NavigateToLogIn)
            }
        }
    }


    private fun userRegister(userRegisterRequest: UserRegisterRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            registerUseCase.invoke(userRegisterRequest).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, isRegistered = true) }
                        _uiEvents.send(OneTimeRegisterEvents.NavigateToLogIn)
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.errorMessage
                            )
                        }
                        _uiEvents.send(OneTimeRegisterEvents.ShowError(result.errorMessage))
                    }
                }
            }
        }
    }


}