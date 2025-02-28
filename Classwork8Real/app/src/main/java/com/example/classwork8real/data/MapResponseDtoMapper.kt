package com.example.classwork8real.data

import com.example.classwork8real.domain.MapResponse

fun MapResponseDto.toDomain():MapResponse {
    return MapResponse(
        lat = this.lat, lan = this.lan, title = this.title, address = this.address
    )
}