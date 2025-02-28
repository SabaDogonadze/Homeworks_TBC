package com.example.classwork8real.data

import retrofit2.Response
import retrofit2.http.GET

interface GetMapLocationService {
    @GET("c4c64996-4ed9-4cbc-8986-43c4990d495a")
    suspend fun getLocations(): Response<List<MapResponseDto>>
}
