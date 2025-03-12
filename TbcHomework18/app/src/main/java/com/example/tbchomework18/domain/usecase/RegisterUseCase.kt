package com.example.tbchomework18.domain.usecase

import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserLogInRequest
import com.example.tbchomework18.data.remote.UserRegisterRequest
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.log_in.LogInResponse
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.domain.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface RegisterUseCase {
    suspend operator fun invoke(userRequest: UserRegisterRequest): Flow<Resource<RegisterResponse>>
}
class RegisterUseCaseImpl @Inject constructor(private val registerRepository: RegisterRepository):RegisterUseCase {
    override suspend operator fun invoke(userRequest: UserRegisterRequest): Flow<Resource<RegisterResponse>> {
       return registerRepository.register(userRequest)
    }
}