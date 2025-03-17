package com.example.tbchomework18.presentation.home

data class HomeUi(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<UserModelUi>,
    val support: SupportUI,
)

data class SupportUI(
    val url: String,
    val text: String,
)

data class UserModelUi(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
)