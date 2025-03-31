package com.example.tbchomework29.data.remote.user_cards

import retrofit2.Response
import retrofit2.http.GET

interface UserCardsService {
    @GET("d689fe3e-6faf-446a-9896-c538de3449fa")
    suspend fun getUserCards(): Response<List<UserCardsDto>>
}