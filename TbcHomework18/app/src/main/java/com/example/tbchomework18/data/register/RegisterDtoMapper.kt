package com.example.tbchomework18.data.register

import com.example.tbchomework18.domain.register.RegisterResponse

fun RegisterDto.toDomain():RegisterResponse{
    return RegisterResponse(id = id, token = token)
}