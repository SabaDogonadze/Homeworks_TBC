package com.example.tbchomework29.data.remote.user_cards

import com.example.tbchomework29.data.common.ApiHelper
import com.example.tbchomework29.domain.common.Resource
import com.example.tbchomework29.domain.user_cards.UserCards
import com.example.tbchomework29.domain.user_cards.UserCardsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserCardsRepositoryImpl @Inject constructor(private val userCardsService: UserCardsService) :
    UserCardsRepository {
    override fun getUserCards(cardId: String?,cardNumber:String?): Flow<Resource<List<UserCards>>> {
        return flow {
            emit(Resource.Loading(load = true))
            val result = ApiHelper.handleHttpRequest {
                userCardsService.getUserCards()
            }
            when (result) {
                is Resource.Success -> {
                    val domainList = result.dataSuccess.map { it.toDomain() }
                    emit(Resource.Success(dataSuccess = domainList))
                }

                is Resource.Error -> emit(Resource.Error(errorMessage = result.errorMessage))
                is Resource.Loading -> emit(Resource.Loading(load = result.loading))
            }
        }
    }
}
