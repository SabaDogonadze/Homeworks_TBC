package com.example.tbchomework18.data.remote.get_user

import com.example.tbchomework18.domain.get_user.Support
import com.example.tbchomework18.domain.get_user.UsersDataResponse

fun UsersDataResponseDto.toDomain():UsersDataResponse{
    return UsersDataResponse(
        page = this.page,
        perPage = this.perPage,
        total = this.total,
        totalPages = this.totalPages,
        data = this.data,
        support = this.support.toDomain()

    )
}

fun SupportDto.toDomain(): Support {
    return Support(
        url = this.url,
        text = this.text
    )
}