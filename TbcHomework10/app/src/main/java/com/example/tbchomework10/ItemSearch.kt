package com.example.tbchomework10

data class ItemSearch(
    val id: Int,
    val title : String,
    val icon : Int,
    val buttonType: ButtonType
)

enum class ButtonType{
    NORMAL,
    ALL;
}
