package com.example.tbchomework18.presentation.log_in

sealed interface LogInEvents {
    data class ActivateLogInButton(val isEnabled : Boolean):LogInEvents
}