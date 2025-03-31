package com.example.tbchomework29.presenter.state

import com.example.tbchomework29.presenter.model.UserCardsUi

data class MoneyTransferState(
    val isLoading:Boolean = false,
    val userCards: List<UserCardsUi> = emptyList(),
    val userCardsFilteredByCardNumber: List<UserCardsUi> = emptyList(),
)