package com.example.tbchomework27.presenter.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework27.domain.common.Resource
import com.example.tbchomework27.domain.usecase.GetSearchVehiclesUseCase
import com.example.tbchomework27.presenter.event.SuggestionVehicleSearchEvent
import com.example.tbchomework27.presenter.event.SuggestionVehicleSearchOneTimeEvents
import com.example.tbchomework27.presenter.mapper.toUi
import com.example.tbchomework27.presenter.state.SearchSuggestionState
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
class SearchViewModel @Inject constructor(private val getSearchVehiclesUseCase: GetSearchVehiclesUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(SearchSuggestionState())
    val state = _state.asStateFlow()

    private val _uiEvents = Channel<SuggestionVehicleSearchOneTimeEvents>()
    val uiEvents get() = _uiEvents.receiveAsFlow()

    fun event(event: SuggestionVehicleSearchEvent) {
        when (event) {
            is SuggestionVehicleSearchEvent.SearchAndLoadVehicles -> {
                getVehicles(event.name)
            }

            SuggestionVehicleSearchEvent.ClearSearch -> _state.update { it.copy(vehicles = emptyList()) }
        }
    }

    private fun getVehicles(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getSearchVehiclesUseCase.invoke(name).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false) }
                        _uiEvents.send(SuggestionVehicleSearchOneTimeEvents.ShowError(result.errorMessage))
                    }

                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                vehicles = result.data?.map { vehicle -> vehicle.toUi() })
                        }
                    }
                }
            }
        }
    }
}
