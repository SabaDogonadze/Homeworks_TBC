package com.example.tbchomework29.presenter.event.from_account_bottom_sheet

sealed class FromAccountBottomSheetEvent {
    data object LoadUserCards : FromAccountBottomSheetEvent()
   // data class OpenMoneyTransferFragment(val cardId: String): FromAccountBottomSheetEvent()
}