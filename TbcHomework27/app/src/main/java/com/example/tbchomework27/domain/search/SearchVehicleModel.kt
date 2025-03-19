package com.example.tbchomework27.domain.search

import com.example.tbchomework27.data.remote.search.SearchVehicleDto

data class SearchVehicleModel(
    val id: String,
    val name: String,
    val nameDe: String,
    val createdAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?,
    val main: String?,
    val children: List<SearchVehicleModel>,
    var depth: Int = 0
)
