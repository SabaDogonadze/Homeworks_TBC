package com.example.tbchomework29.presenter.screen.money_transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework29.domain.common.Resource
import com.example.tbchomework29.domain.usecase.GetUserCardsUseCase
import com.example.tbchomework29.presenter.event.money_transfer.MoneyTransferEvent
import com.example.tbchomework29.presenter.event.money_transfer.MoneyTransferSideEvent
import com.example.tbchomework29.presenter.mapper.toUi
import com.example.tbchomework29.presenter.state.MoneyTransferState
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
class MoneyTransferViewModel @Inject constructor(private val getUserCardsUseCase: GetUserCardsUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(MoneyTransferState())
    val state = _state.asStateFlow()

    private val _uiEvents = MutableSharedFlow<MoneyTransferSideEvent>()
    val uiEvents = _uiEvents.asSharedFlow() // not used

    //handling events
    fun event(event: MoneyTransferEvent) {
        when (event) {
            is MoneyTransferEvent.LoadUserCards -> getUserCards(event.id)
            is MoneyTransferEvent.LoadUserCardsByCardNumber -> getUserCardsFromCardNumber(event.cardNumber)
        }
    }

    private fun getUserCards(cardId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCardsUseCase.invoke(
                cardId
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false) }
                    }

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        val userCards = result.data?.map { cards ->
                            cards.toUi()
                        }
                            ?: emptyList()
                        _state.update {
                            it.copy(
                                isLoading = false,
                                userCards = userCards
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getUserCardsFromCardNumber(cardNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCardsUseCase.invoke(
                cardNumber
            ).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false) }
                    }

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        val userCardsFilteredByCardNumber = result.data?.map { cards ->
                            cards.toUi()
                        }
                            ?: emptyList()
                        _state.update {
                            it.copy(
                                isLoading = false,
                                userCardsFilteredByCardNumber = userCardsFilteredByCardNumber
                            )
                        }
                    }
                }
            }
        }
    }
}

