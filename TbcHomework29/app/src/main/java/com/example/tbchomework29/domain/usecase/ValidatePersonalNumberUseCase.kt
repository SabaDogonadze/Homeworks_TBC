package com.example.tbchomework29.domain.usecase

import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ValidatePersonalNumberUseCase {
    operator fun invoke(personalNumber: String): Flow<Resource<String>>
}
class ValidatePersonalNumberUseCaseImpl @Inject constructor() : ValidatePersonalNumberUseCase {
    override operator fun invoke(personalNumber: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))

        if (personalNumber.length != 11 || !personalNumber.all { it.isDigit() }) {
            emit(Resource.Error("Personal number must consist of exactly 11 digits.")) // what should i do now? :dd
            return@flow
        }
        emit(Resource.Success(personalNumber))
    }
}