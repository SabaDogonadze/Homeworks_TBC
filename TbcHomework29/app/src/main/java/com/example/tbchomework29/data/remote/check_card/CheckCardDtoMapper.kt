package com.example.tbchomework29.data.remote.check_card

import com.example.tbchomework29.domain.check_card.CheckCard

fun CheckCardDto.toDomain(): CheckCard {
    return CheckCard(status = status)
}