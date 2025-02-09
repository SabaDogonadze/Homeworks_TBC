package com.example.tbchomework18.data.log_in

import com.example.tbchomework18.data.remote.UserLogInRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {
    @POST("login")
    suspend fun logIn(@Body userRequest: UserLogInRequest): Response<LogInDto>
}