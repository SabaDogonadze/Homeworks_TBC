package com.example.tbchomework15.data

import com.example.tbchomework15.R
import com.example.tbchomework15.model.CardModel
import com.example.tbchomework15.model.CardType

object CardData {
    var cardData: MutableList<CardModel> = mutableListOf(
        CardModel(
            type = CardType.VISA,
            cardNumber1 = 1234,
            cardNumber2 = 5678,
            cardNumber3 = 1234,
            cardNumber4 = 5678,
            cardBackground = R.color.blue,
            cardLogo = R.drawable.ic_visa,
            cardUserName = "Jack",
            cardExpiration = "12/23",
            cardValidation = "123"
        ),
        CardModel(
             type = CardType.MASTERCARD,
            cardNumber1 = 1234,
            cardNumber2 = 5678,
            cardNumber3 = 1234,
            cardNumber4 = 5678,
            cardBackground = R.color.gold,
            cardLogo = R.drawable.ic_mastercard,
            cardUserName = "Jack",
            cardExpiration = "12/23",
            cardValidation = "123"
        )
    )
}