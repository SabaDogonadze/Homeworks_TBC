package com.example.tbchomework29.data.remote.user_cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCardsDto(
    val id: Int,
    @SerialName("account_name")
    val accountName: String,
    @SerialName("account_number")
    val accountNumber: String,
    @SerialName("valute_type")
    val valuteType: String,
    @SerialName("card_type")
    val cardType: String,
    val balance: Int,
    @SerialName("card_logo")
    val cardLogo: String? = null
)
