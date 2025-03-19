package com.example.tbchomework27.data.remote.search

import com.squareup.moshi.Json

data class SearchVehicleDto(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "name_de")
    val nameDe: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "bgl_number")
    val bglNumber: String?,
    @Json(name = "bgl_variant")
    val bglVariant: String?,
    @Json(name = "order_id")
    val orderId: Int?,
    val main: String?,
    val children: List<SearchVehicleDto>
)
