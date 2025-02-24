package com.example.tbchomework24.data.home

import com.example.tbchomework24.domain.StoryDataResponse

fun StoryItemDto.toDomain(): StoryDataResponse {
    return StoryDataResponse(
        id = this.id, cover = this.cover, title = this.title
    )
}