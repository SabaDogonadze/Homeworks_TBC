package com.example.tbchomework18.data.remote.register

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun register(@Body userRequest: UserRegisterRequest): Response<RegisterDto>
}