package com.example.classwork8real.data

import com.squareup.moshi.Json

data class MapResponseDto(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lan")
    val lan: Double,
    @Json(name = "title")
    val title: String,
    @Json(name = "address")
    val address: String,
)