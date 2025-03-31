package com.example.tbchomework29.presenter.mapper

import com.example.tbchomework29.domain.user_cards.UserCards
import com.example.tbchomework29.presenter.model.UserCardsUi

fun UserCards.toUi(): UserCardsUi {
    return UserCardsUi(
        id = id,
        accountName =accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}