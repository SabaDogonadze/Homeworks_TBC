package com.example.tbchomework29.presenter.mapper

import com.example.tbchomework29.domain.check_card.CheckCard
import com.example.tbchomework29.presenter.model.CheckCardUi


fun CheckCard.toUi(): CheckCardUi {
    return CheckCardUi(
        status = status
    )
}