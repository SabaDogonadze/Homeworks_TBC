package com.example.tbchomework12

data class FilterButton(
    val id:Int,
    val text:String,
    val status: Status,
    var selected: Selected
)

enum class Selected{
    SELECTED,
    NOT_SELECTED
}