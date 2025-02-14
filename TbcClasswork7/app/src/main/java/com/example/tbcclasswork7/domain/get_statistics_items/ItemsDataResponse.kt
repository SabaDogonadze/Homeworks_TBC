package com.example.tbcclasswork7.domain.get_statistics_items

data class ItemsDataResponse(
    val id:Int,
    val cover:String,
    val price:String,
    val title:String,
    val location:String,
    val reactionCount:Int,
    val rate:Float?
)