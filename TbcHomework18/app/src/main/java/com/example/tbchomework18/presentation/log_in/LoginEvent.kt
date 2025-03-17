package com.example.tbchomework18.presentation.log_in

// if we have a constructor we should use data class
sealed interface LogInEvent {
    data class LoginButtonClicked(val email: String, val password: String,val rememberMe: Boolean) : LogInEvent
}

// also there is a practice where there are separate classes for one time events
sealed class OneTimeLoginInEvents {
    data object NavigateToHome : OneTimeLoginInEvents()
    data class ShowError(val message: String) : OneTimeLoginInEvents()
}