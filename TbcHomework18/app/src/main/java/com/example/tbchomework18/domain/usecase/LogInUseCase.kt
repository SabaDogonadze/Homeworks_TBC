package com.example.tbchomework18.domain.usecase

import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserLogInRequest
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.log_in.LogInResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LogInUseCase {
    suspend operator fun invoke(userLoginRequest: UserLogInRequest):Flow<Resource<LogInResponse>>
}
class LogInUseCaseImpl @Inject constructor(private val logInRepository: LogInRepository):LogInUseCase {
    override suspend operator fun invoke(userLoginRequest: UserLogInRequest): Flow<Resource<LogInResponse>> {
        return logInRepository.logIn(userLoginRequest)
    }
}