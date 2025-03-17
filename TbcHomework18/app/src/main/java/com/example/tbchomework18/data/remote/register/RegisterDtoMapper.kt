package com.example.tbchomework18.data.remote.register

import com.example.tbchomework18.domain.register.RegisterResponse

fun RegisterDto.toDomain():RegisterResponse{
    return RegisterResponse(id = id, token = token)
}