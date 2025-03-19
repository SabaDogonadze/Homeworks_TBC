package com.example.tbchomework27.presenter.event

sealed class SuggestionVehicleSearchEvent {
    data class SearchAndLoadVehicles(val name:String):SuggestionVehicleSearchEvent()
    data object ClearSearch : SuggestionVehicleSearchEvent()
}

sealed class SuggestionVehicleSearchOneTimeEvents {
    data class ShowError(val message: String) : SuggestionVehicleSearchOneTimeEvents()
}