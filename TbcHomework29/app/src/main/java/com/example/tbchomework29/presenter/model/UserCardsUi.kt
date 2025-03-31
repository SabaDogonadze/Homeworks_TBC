package com.example.tbchomework29.presenter.model

data class UserCardsUi(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String? = null
)
