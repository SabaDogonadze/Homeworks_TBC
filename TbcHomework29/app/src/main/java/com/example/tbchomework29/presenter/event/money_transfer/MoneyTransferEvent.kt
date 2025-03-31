package com.example.tbchomework29.presenter.event.money_transfer

sealed class MoneyTransferEvent {
    data class LoadUserCards(val id:String) : MoneyTransferEvent()
    data class LoadUserCardsByCardNumber(val cardNumber:String) : MoneyTransferEvent()
}