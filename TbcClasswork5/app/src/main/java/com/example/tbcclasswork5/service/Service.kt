package com.example.tbcclasswork5.service

import com.example.tbcclasswork5.model.ServerResponse
import retrofit2.Response
import retrofit2.http.GET

interface  Service {
    @GET("f3f41821-7434-471f-9baa-ae3dee984e6d")
    suspend fun getUsersData():Response<ServerResponse>
}