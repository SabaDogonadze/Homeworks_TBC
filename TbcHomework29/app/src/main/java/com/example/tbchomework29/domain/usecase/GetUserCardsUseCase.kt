package com.example.tbchomework29.domain.usecase

import com.example.tbchomework29.domain.common.Resource
import com.example.tbchomework29.domain.user_cards.UserCards
import com.example.tbchomework29.domain.user_cards.UserCardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUserCardsUseCase {
    operator fun invoke(cardId: String? = null,cardNumber:String? = null): Flow<Resource<List<UserCards>>>
}

class GetUserCardsUseCaseImpl @Inject constructor(
    private val userCardsRepository: UserCardsRepository
) : GetUserCardsUseCase {
    override operator fun invoke(cardId:String?,cardNumber:String?): Flow<Resource<List<UserCards>>> {
        return userCardsRepository.getUserCards()
    }
}