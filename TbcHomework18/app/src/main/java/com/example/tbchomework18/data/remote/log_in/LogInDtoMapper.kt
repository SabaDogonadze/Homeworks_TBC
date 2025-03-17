package com.example.tbchomework18.data.remote.log_in

import com.example.tbchomework18.domain.log_in.LogInResponse

fun LogInDto.toDomain(): LogInResponse {
    return LogInResponse(token = token)
}