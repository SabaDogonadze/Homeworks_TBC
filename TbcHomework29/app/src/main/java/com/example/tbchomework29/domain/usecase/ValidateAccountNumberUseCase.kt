package com.example.tbchomework29.domain.usecase

import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ValidateAccountNumberUseCase {
    operator fun invoke(accountNumber: String): Flow<Resource<String>>
}

class ValidateAccountNumberUseCaseImpl @Inject constructor() : ValidateAccountNumberUseCase {
    override operator fun invoke(accountNumber: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))
        if (accountNumber.length != 23) {
            emit(Resource.Error("Account number must consist of exactly 23 symbols."))
            return@flow
        }
        emit(Resource.Success(accountNumber))
    }
}