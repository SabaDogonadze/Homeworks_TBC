package com.example.tbchomework29.presenter.event.to_account_bottom_sheet

sealed class ToAccountBottomSheetEvent {
    data object LoadUserCards : ToAccountBottomSheetEvent()
    data class PersonalIdButtonClicked(val id:String): ToAccountBottomSheetEvent()
    data class PhoneNumberButtonClicked(val phoneNumber:String) : ToAccountBottomSheetEvent()
    data class CardNumberButtonClicked(val cardNumber:String) :ToAccountBottomSheetEvent()
}
