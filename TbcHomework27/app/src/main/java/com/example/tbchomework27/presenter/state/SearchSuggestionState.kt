package com.example.tbchomework27.presenter.state

import com.example.tbchomework27.presenter.model.SuggestionVehicleUi

data class SearchSuggestionState(
    val isLoading:Boolean = false,
    val vehicles:List<SuggestionVehicleUi>? = null
)
