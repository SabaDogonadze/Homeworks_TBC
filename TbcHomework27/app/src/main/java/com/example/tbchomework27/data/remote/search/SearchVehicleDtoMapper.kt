package com.example.tbchomework27.data.remote.search

import com.example.tbchomework27.domain.search.SearchVehicleModel

fun SearchVehicleDto.toDomain():SearchVehicleModel{
    return SearchVehicleModel(
        id = id,
        name =name,
        nameDe = nameDe,
        createdAt = createdAt,
        bglNumber = bglNumber,
        bglVariant = bglVariant,
        orderId = orderId,
        main = main,
        children = children.map { it.toDomain() }
    )
}