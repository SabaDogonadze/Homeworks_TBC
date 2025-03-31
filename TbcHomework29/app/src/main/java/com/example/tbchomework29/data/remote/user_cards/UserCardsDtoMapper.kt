package com.example.tbchomework29.data.remote.user_cards

import com.example.tbchomework29.domain.user_cards.UserCards

fun UserCardsDto.toDomain():UserCards{
    return UserCards(
        id = id,
        accountName =accountName,
        accountNumber = accountNumber,
        valuteType = valuteType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo
    )
}