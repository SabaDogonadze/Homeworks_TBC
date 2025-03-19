package com.example.tbchomework27.data.remote.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchVehicleService {
    @GET("499e0ffd-db69-4955-8d86-86ee60755b9c")
    suspend fun searchVehicles(@Query("search") title: String): Response<List<SearchVehicleDto>>
}