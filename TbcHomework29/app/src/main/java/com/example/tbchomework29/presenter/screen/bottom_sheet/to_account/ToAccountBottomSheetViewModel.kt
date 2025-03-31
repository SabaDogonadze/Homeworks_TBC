package com.example.tbchomework29.presenter.screen.bottom_sheet.to_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework29.domain.common.Resource
import com.example.tbchomework29.domain.usecase.CheckAccountStatusCoordinatorUseCase
import com.example.tbchomework29.domain.usecase.ValidatePersonalNumberUseCase
import com.example.tbchomework29.domain.usecase.ValidatePhoneNumberUseCase
import com.example.tbchomework29.presenter.event.to_account_bottom_sheet.ToAccountBottomSheetEvent
import com.example.tbchomework29.presenter.event.to_account_bottom_sheet.ToAccountBottomSheetUiEvent
import com.example.tbchomework29.presenter.state.ToAccountBottomSheetState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToAccountBottomSheetViewModel @Inject constructor(
    //private val validateAccountNumberUseCase: ValidateAccountNumberUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validatePersonalNumberUseCase: ValidatePersonalNumberUseCase,
    private val checkAccountStatusCoordinatorUseCase: CheckAccountStatusCoordinatorUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ToAccountBottomSheetState())
    val state = _state.asStateFlow()

    private val _uiEvents = MutableSharedFlow<ToAccountBottomSheetUiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    //handling events
    fun event(event: ToAccountBottomSheetEvent) {
        when (event) {

            is ToAccountBottomSheetEvent.CardNumberButtonClicked -> validateCardNumber(event.cardNumber)
            is ToAccountBottomSheetEvent.PersonalIdButtonClicked -> validatePersonalNumber(
                personalNumber = event.id
            )

            is ToAccountBottomSheetEvent.PhoneNumberButtonClicked -> validatePhoneNumber(event.phoneNumber)
            ToAccountBottomSheetEvent.LoadUserCards -> TODO()
        }
    }

    private fun validatePersonalNumber(personalNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            validatePersonalNumberUseCase(personalNumber).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false)
                        }
                        _uiEvents.emit(ToAccountBottomSheetUiEvent.ShowError(resource.errorMessage))
                    }

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _state.update { it.copy(isLoading = false, userId = resource.dataSuccess) }
                        _uiEvents.emit(
                            ToAccountBottomSheetUiEvent.OpenMoneyTransferFragment(
                                resource.dataSuccess
                            )
                        )
                    }
                }
            }
        }
    }

    private fun validatePhoneNumber(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            validatePhoneNumberUseCase(phoneNumber).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false)
                        }
                        _uiEvents.emit(ToAccountBottomSheetUiEvent.ShowError(resource.errorMessage))
                    }
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                userPhoneNumber = resource.dataSuccess
                            )
                        }
                        _uiEvents.emit(
                            ToAccountBottomSheetUiEvent.OpenMoneyTransferFragment(
                                resource.dataSuccess
                            )
                        )
                    }
                }
            }
        }
    }

    private fun validateCardNumber(cardNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            checkAccountStatusCoordinatorUseCase(cardNumber).collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(isLoading = false)
                        }
                        _uiEvents.emit(ToAccountBottomSheetUiEvent.ShowError(resource.errorMessage))
                    }
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                userCardNumber = resource.dataSuccess.status
                            )
                        }
                        _uiEvents.emit(
                            ToAccountBottomSheetUiEvent.OpenMoneyTransferFragment(
                                cardNumber
                            )
                        )
                    }
                }
            }
        }
    }

}