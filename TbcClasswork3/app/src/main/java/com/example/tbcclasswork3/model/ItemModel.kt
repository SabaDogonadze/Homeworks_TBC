package com.example.tbcclasswork3.model

data class ItemModel(
    val id:Int,
    val buttonImage:Int,
    var type: ButtonType = ButtonType.EMPTY_BUTTON
)
enum class ButtonType{
    X_BUTTON,
    O_BUTTON,
    EMPTY_BUTTON
}
