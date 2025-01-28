package com.example.tbchomework20.service

import com.example.tbchomework20.data.ServerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApiService {
    @GET("users")
    suspend fun getUsersData(@Query("page") page: Int): Response<ServerResponse>
}
