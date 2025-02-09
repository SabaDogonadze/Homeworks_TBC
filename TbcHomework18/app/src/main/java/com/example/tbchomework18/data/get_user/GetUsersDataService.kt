package com.example.tbchomework18.data.get_user

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetUsersDataService {
    @GET("users")
    suspend fun getUsersData() : Response<UsersDataResponseDto>
}