package com.example.tbchomework29.presenter.state

import com.example.tbchomework29.presenter.model.UserCardsUi

data class ToAccountBottomSheetState(
    val isLoading: Boolean = false,
    val userCards: List<UserCardsUi> = emptyList(),
    val userPhoneNumber : String = "",
    val userId:String = "",
    val userCardNumber:String = ""
)

