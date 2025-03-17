package com.example.tbchomework18.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tbchomework18.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    private val _state = MutableStateFlow(HomeStateUi())
    val state = _state.asStateFlow()

    private val _uiEvents = Channel<OneTimeHomeEvents>()
    val uiEvents get() = _uiEvents.receiveAsFlow()


    fun event(event: HomeEvent) {
        when (event) {
            is HomeEvent.ProfileButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _uiEvents.send(OneTimeHomeEvents.NavigateToProfile)
                }
            }
        }
    }

    val usersFlow = getUserUseCase.invoke()
        .map { pagingData ->
            pagingData.map { it.toPresenter() } // i am reading this from room i guess
        }
        .cachedIn(viewModelScope)
}