package com.example.tbchomework29.domain.usecase

import com.example.tbchomework29.domain.check_card.CheckCard
import com.example.tbchomework29.domain.check_card.CheckCardRepository
import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CheckUserCardStatusUseCase {
    operator fun invoke(accountNumber: String): Flow<Resource<CheckCard>>
}

class CheckUserCardStatusUseCaseImpl @Inject constructor(
    private val repository: CheckCardRepository,
) : CheckUserCardStatusUseCase {
    override operator fun invoke(accountNumber: String): Flow<Resource<CheckCard>>{
        return repository.checkUserCardStatus(accountNumber)
    }
}
