package com.example.tbchomework18.presentation.profile

sealed interface ProfileEvent{
    data object LogOutButtonClicked : ProfileEvent
}

sealed class OneTimeProfileEvents {
    data object NavigateToLogIn : OneTimeProfileEvents()
    data class ShowError(val message: String) : OneTimeProfileEvents()
}