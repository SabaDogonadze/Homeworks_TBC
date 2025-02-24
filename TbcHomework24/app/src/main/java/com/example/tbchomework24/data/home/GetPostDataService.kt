package com.example.tbchomework24.data.home

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface GetPostDataService {
    @GET("1ba8b612-8391-41e5-8560-98e4a48decc7")
    suspend fun getPostData(): Response<List<PostItemDto>>
}