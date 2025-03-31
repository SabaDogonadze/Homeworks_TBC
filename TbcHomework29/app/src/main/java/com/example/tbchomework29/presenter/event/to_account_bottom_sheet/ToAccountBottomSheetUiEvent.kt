package com.example.tbchomework29.presenter.event.to_account_bottom_sheet


sealed class ToAccountBottomSheetUiEvent {
   data class OpenMoneyTransferFragment(val cardNumber: String): ToAccountBottomSheetUiEvent()
    data class ShowError(val message: String) : ToAccountBottomSheetUiEvent()
}



/*
data object PersonalIdButtonClicked: ToAccountBottomSheetUiEvent()
data object PhoneNumberButtonClicked : ToAccountBottomSheetUiEvent()
data object CardNumberButtonClicked :ToAccountBottomSheetUiEvent()*/
