package com.example.tbchomework29.domain.usecase

import com.example.tbchomework29.domain.check_card.CheckCard
import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CheckAccountStatusCoordinatorUseCase {
    operator fun invoke(accountNumber: String): Flow<Resource<CheckCard>>
}

class CheckAccountStatusCoordinatorUseCaseImpl @Inject constructor(
    private val validateAccountNumberUseCase: ValidateAccountNumberUseCase,
    private val checkUserCardStatusUseCase: CheckUserCardStatusUseCase
) : CheckAccountStatusCoordinatorUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    override operator fun invoke(accountNumber: String): Flow<Resource<CheckCard>> =
        validateAccountNumberUseCase(accountNumber).flatMapConcat { validationResource ->
            when (validationResource) {
                is Resource.Success -> checkUserCardStatusUseCase(accountNumber)
                is Resource.Error -> flow { emit(Resource.Error(validationResource.error ?: "Invalid account number.")) }
                is Resource.Loading -> flow { emit(Resource.Loading(true)) }
            }
        }
}
