package com.example.tbchomework29.data.remote.check_card

import com.example.tbchomework29.data.remote.user_cards.UserCardsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CheckCardService {
    @GET("29d002d4-3ccd-4eaa-95eb-a9d1601ce123")
    suspend fun checkUserCard(@Query("account_number") accountNumber: String): Response<CheckCardDto>
}