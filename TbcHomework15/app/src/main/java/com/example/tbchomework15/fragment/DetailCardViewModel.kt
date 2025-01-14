package com.example.tbchomework15.fragment

import androidx.lifecycle.ViewModel
import com.example.tbchomework15.data.CardData
import com.example.tbchomework15.model.CardModel
import com.example.tbchomework15.model.CardNumberParts
import com.example.tbchomework15.model.CardType

class DetailCardViewModel:ViewModel() {

    fun saveUserData(userName:String, cardNumber:String, expirationDate:String, cardCvv:String, cardType : CardType, cardBackground:Int, cardLogo:Int){
        val cardParts = manageUserCardNumber(cardNumber)
    CardData.cardData.add(
        CardModel(
            type = cardType,
            cardNumber1 = cardParts.cardNumber1,
            cardNumber2 = cardParts.cardNumber2,
            cardNumber3 = cardParts.cardNumber3,
            cardNumber4 = cardParts.cardNumber4,
            cardBackground = cardBackground,
            cardLogo = cardLogo,
            cardUserName = userName,
            cardExpiration = expirationDate,
            cardValidation = cardCvv
        )
    )
    }
    private fun manageUserCardNumber(cardNumber: String): CardNumberParts {
        val cardNumber1 =  cardNumber.substring(0,4).toInt()
        val cardNumber2 = cardNumber.substring(4,8).toInt()
        val cardNumber3 = cardNumber.substring(8,12).toInt()
        val cardNumber4 = cardNumber.substring(12,16).toInt()

        return CardNumberParts(cardNumber1, cardNumber2, cardNumber3, cardNumber4)
    }

    fun isCardNumberValid(cardNumber:String):Boolean {
       return cardNumber.length == 16 && cardNumber.all { it.isDigit() }
    }

    fun isCardExpirationValid(cardExpiration:String):Boolean{
        return cardExpiration.length == 4 && cardExpiration.all { it.isDigit() }
    }

    fun isCardCvvValid(cardExpiration:String):Boolean{
        return cardExpiration.length == 3 && cardExpiration.all { it.isDigit() }
    }
}