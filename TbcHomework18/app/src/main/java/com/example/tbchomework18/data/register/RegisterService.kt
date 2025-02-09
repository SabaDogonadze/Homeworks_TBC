package com.example.tbchomework18.data.register

import com.example.tbchomework18.data.remote.UserRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("register")
    suspend fun register(@Body userRequest: UserRegisterRequest): Response<RegisterDto>
}