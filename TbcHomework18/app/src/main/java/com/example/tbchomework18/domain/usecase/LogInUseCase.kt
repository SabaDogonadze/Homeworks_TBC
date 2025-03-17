package com.example.tbchomework18.domain.usecase

import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.data.remote.register.UserLogInRequest
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.log_in.LogInResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LogInUseCase {
    suspend operator fun invoke(userLoginRequest: UserLogInRequest,rememberMe:Boolean):Flow<Resource<LogInResponse>>
}
class LogInUseCaseImpl @Inject constructor(private val logInRepository: LogInRepository):LogInUseCase {
    override suspend operator fun invoke(userLoginRequest: UserLogInRequest,rememberMe:Boolean): Flow<Resource<LogInResponse>> {
        return logInRepository.logIn(userLoginRequest,rememberMe)
    }
}