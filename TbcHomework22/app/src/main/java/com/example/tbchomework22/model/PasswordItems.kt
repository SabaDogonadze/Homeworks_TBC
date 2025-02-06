package com.example.tbchomework22.model

data class PasswordItems(
    val id:Int,
    val item:Int,
    val passwordItemType: PasswordItemType
)
enum class PasswordItemType{
    NUMBER,
    FINGER_PRINT,
    DELETE
}

data class PasswordOvals(
    val id:Int,
    var ovalStatus: OvalStatus
)

enum class OvalStatus{
    CHECKED,
    UNCHECKED
}
