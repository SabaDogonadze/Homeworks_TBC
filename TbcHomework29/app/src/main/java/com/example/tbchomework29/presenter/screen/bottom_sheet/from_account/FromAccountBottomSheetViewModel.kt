package com.example.tbchomework29.presenter.screen.bottom_sheet.from_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework29.domain.common.Resource
import com.example.tbchomework29.domain.usecase.GetUserCardsUseCase
import com.example.tbchomework29.presenter.event.from_account_bottom_sheet.FromAccountBottomSheetEvent
import com.example.tbchomework29.presenter.event.from_account_bottom_sheet.FromAccountBottomSheetSideEvent
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
class FromAccountBottomSheetViewModel @Inject constructor(private val getUserCardsUseCase: GetUserCardsUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(MoneyTransferState())
    val state = _state.asStateFlow()

    private val _uiEvents = MutableSharedFlow<FromAccountBottomSheetSideEvent>()
    val uiEvents = _uiEvents.asSharedFlow() // not used

    //handling events
    fun event(event: FromAccountBottomSheetEvent) {
        when (event) {
            FromAccountBottomSheetEvent.LoadUserCards -> getUserCards()
            //is FromAccountBottomSheetEvent.OpenMoneyTransferFragment -> onCardClicked(id = event.cardId)
        }
    }

  /*  fun sideEvent(event: FromAccountBottomSheetSideEvent) {
        when (event) {
            is FromAccountBottomSheetSideEvent.OpenMoneyTransferFragment -> onCardClicked(id = event.cardId)
            is FromAccountBottomSheetSideEvent.ShowError -> TODO()
        }
    }*/

  /*  private fun onCardClicked(id:String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvents.emit(FromAccountBottomSheetSideEvent.OpenMoneyTransferFragment(id))
        }
    }*/

    private fun getUserCards() {
        viewModelScope.launch(Dispatchers.IO) {
            getUserCardsUseCase.invoke(
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
}