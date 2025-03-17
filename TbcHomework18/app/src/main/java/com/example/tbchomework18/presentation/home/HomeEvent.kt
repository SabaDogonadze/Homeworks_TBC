package com.example.tbchomework18.presentation.home

sealed interface HomeEvent {
    data object ProfileButtonClicked : HomeEvent
}

sealed class OneTimeHomeEvents {
    data object NavigateToProfile : OneTimeHomeEvents()
    data class ShowError(val message: String) : OneTimeHomeEvents()
}