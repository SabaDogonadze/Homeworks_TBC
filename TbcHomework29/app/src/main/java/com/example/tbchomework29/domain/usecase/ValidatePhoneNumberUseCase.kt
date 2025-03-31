package com.example.tbchomework29.domain.usecase

import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ValidatePhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): Flow<Resource<String>>
}
class ValidatePhoneNumberUseCaseImpl @Inject constructor() : ValidatePhoneNumberUseCase {
    override operator fun invoke(phoneNumber: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading(true))

        if (phoneNumber.length != 9 || !phoneNumber.all { it.isDigit() }) {
            emit(Resource.Error("Phone number must consist of exactly 9 digits."))
            return@flow
        }

        emit(Resource.Success(phoneNumber))
    }
}