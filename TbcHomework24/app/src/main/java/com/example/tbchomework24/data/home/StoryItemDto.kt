package com.example.tbchomework24.data.home

import com.squareup.moshi.Json

data class StoryItemDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "title")
    val title: String
)
