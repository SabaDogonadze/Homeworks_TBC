package com.example.tbchomework29.presenter.event.from_account_bottom_sheet

sealed class FromAccountBottomSheetSideEvent{
    data class OpenMoneyTransferFragment(val cardId: String): FromAccountBottomSheetSideEvent()
    data class ShowError(val message: String) : FromAccountBottomSheetSideEvent()
}