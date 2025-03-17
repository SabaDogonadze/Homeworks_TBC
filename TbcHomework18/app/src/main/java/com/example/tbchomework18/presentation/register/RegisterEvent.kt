package com.example.tbchomework18.presentation.register

sealed interface RegisterEvent {
    data class RegisterButtonClicked(val email: String, val password: String,val repeatPassword:String) : RegisterEvent
    data object BackButtonClicked:RegisterEvent
}

sealed class OneTimeRegisterEvents {
    data object NavigateToLogIn : OneTimeRegisterEvents()
    data class ShowError(val message: String) : OneTimeRegisterEvents()
}