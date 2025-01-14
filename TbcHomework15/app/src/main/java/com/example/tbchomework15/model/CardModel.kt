package com.example.tbchomework15.model

import java.util.UUID

data class CardModel(
    val id: String = UUID.randomUUID().toString(),
    val type: CardType,
    val cardNumber1 : Int,
    val cardNumber2 : Int,
    val cardNumber3 : Int,
    val cardNumber4 : Int,
    val cardBackground:Int,
    var cardExpiration : String,
    val cardLogo : Int,
    val cardUserName : String,
    val cardValidation : String

)
enum class CardType{
    VISA,
    MASTERCARD
}

data class CardNumberParts(
    val cardNumber1: Int,
    val cardNumber2: Int,
    val cardNumber3: Int,
    val cardNumber4: Int
)
