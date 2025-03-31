package com.example.tbchomework29.presenter.event.money_transfer

sealed class MoneyTransferSideEvent {
    data class ShowError(val message: String) : MoneyTransferSideEvent()
    data object OpenAccountBottomSheet : MoneyTransferSideEvent()
}