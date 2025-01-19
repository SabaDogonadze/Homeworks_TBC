package com.example.tbchomework17.service

import com.example.tbchomework17.model.UserLoginRequest
import com.example.tbchomework17.model.UserLoginResponse
import com.example.tbchomework17.model.UserRegisterRequest
import com.example.tbchomework17.model.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface  Service {
    @POST("login")
    suspend fun login(@Body request: UserLoginRequest): Response<UserLoginResponse>

    @POST("request")
    suspend fun register(@Body request : UserRegisterRequest) : Response<UserRegisterResponse>
}