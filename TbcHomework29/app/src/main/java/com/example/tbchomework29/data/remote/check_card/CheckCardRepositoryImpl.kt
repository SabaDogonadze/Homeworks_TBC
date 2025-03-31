package com.example.tbchomework29.data.remote.check_card

import com.example.tbchomework29.data.common.ApiHelper
import com.example.tbchomework29.domain.check_card.CheckCard
import com.example.tbchomework29.domain.check_card.CheckCardRepository
import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckCardRepositoryImpl @Inject constructor(private val checkCardService: CheckCardService) :
    CheckCardRepository {
    override fun checkUserCardStatus(accountNumber: String): Flow<Resource<CheckCard>> {
        return flow {
            emit(Resource.Loading(load = true))
            val result = ApiHelper.handleHttpRequest {
                checkCardService.checkUserCard(accountNumber)
            }
            when (result) {
                is Resource.Success -> {
                    val status = result.dataSuccess.toDomain()
                    emit(Resource.Success(dataSuccess = status))
                }

                is Resource.Error -> emit(Resource.Error(errorMessage = result.errorMessage))
                is Resource.Loading -> emit(Resource.Loading(load = result.loading))
            }
        }
    }
}
