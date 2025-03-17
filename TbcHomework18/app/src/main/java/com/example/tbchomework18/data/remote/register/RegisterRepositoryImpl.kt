package com.example.tbchomework18.data.remote.register

import com.example.tbchomework18.data.common.ApiHelper
import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.domain.common.mapResource
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.domain.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerService: RegisterService) :
    RegisterRepository {
    override suspend fun register(userRequest: UserRegisterRequest): Flow<Resource<RegisterResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { registerService.register(userRequest = userRequest) }
            )
            emit(result)
        }.mapResource { registerResponse ->
            registerResponse.toDomain()
        }
    }
}

