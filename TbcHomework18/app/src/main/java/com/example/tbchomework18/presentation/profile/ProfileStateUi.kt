package com.example.tbchomework18.presentation.profile

data class ProfileStateUi(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val email: String? = null
)