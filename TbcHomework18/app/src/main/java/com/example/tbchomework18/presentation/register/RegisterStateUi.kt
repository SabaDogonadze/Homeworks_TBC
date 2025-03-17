package com.example.tbchomework18.presentation.register

data class RegisterStateUi(
    val isLoading: Boolean = false,
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val errorMessage: String? = null,
    val isRegistered: Boolean = false
)
