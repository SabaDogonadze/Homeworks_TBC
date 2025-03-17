package com.example.tbchomework18.presentation.log_in

data class LoginStateUi(
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false
)
