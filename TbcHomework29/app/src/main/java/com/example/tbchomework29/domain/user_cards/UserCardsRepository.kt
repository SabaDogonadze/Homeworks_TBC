package com.example.tbchomework29.domain.user_cards

import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow

interface UserCardsRepository {
    fun getUserCards(cardId: String? = null,cardNumber:String? = null): Flow<Resource<List<UserCards>>>
}