package com.example.tbchomework14.model

data class ItemModel(
    val id:Int,
    val image:Int,
    val name : String,
    val color : Int,
    val quantity : Int,
    val status : ItemStatus,
    val price : Int
)
enum class ItemStatus{
    ACTIVE,
    COMPLETED
}
