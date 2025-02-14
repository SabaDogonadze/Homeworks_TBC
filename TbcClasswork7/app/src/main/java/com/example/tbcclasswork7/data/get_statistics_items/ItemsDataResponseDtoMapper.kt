package com.example.tbcclasswork7.data.get_statistics_items

import com.example.tbcclasswork7.domain.get_statistics_items.ItemsDataResponse

fun ItemsDataResponseDto.toDomain(): ItemsDataResponse {
    return ItemsDataResponse(
        id = this.id,
        cover = this.cover,
        price = this.price,
        title = this.title,
        location = this.location,
        reactionCount = this.reactionCount,
        rate = this.rate
    )
}