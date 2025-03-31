package com.example.tbchomework29.domain.user_cards

import kotlinx.serialization.SerialName

data class UserCards(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valuteType: String,
    val cardType: String,
    val balance: Int,
    val cardLogo: String? = null
)
