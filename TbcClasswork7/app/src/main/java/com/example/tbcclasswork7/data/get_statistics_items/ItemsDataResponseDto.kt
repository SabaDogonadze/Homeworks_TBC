package com.example.tbcclasswork7.data.get_statistics_items

import com.squareup.moshi.Json

data class ItemsDataResponseDto(
    @Json(name ="id")
    val id:Int,
    @Json(name ="cover")
    val cover:String,
    @Json(name ="price")
    val price:String,
    @Json(name ="title")
    val title:String,
    @Json(name ="location")
    val location:String,
    @Json(name ="reaction_count")
    val reactionCount:Int,
    @Json(name ="rate")
    val rate:Float?
)
