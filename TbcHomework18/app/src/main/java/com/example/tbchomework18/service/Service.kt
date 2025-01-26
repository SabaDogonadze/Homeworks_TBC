package com.example.tbchomework18.service

import com.example.tbchomework18.data.HomePageServerResponse
import com.example.tbchomework18.data.UserLogInRequest
import com.example.tbchomework18.data.UserLogInResponse
import com.example.tbchomework18.data.UserRegisterRequest
import com.example.tbchomework18.data.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NetworkService {
    @POST("login")
    suspend fun logIn(@Body userRequest: UserLogInRequest): Response<UserLogInResponse>

    @POST("register")
    suspend fun register(@Body userRequest: UserRegisterRequest): Response<UserRegisterResponse>

    @GET("users")
    suspend fun getUsersData(/*@Query("page") page: Int*/):Response<HomePageServerResponse>
}