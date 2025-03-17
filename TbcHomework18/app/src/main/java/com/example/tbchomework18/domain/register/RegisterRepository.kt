package com.example.tbchomework18.domain.register

import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.data.remote.register.UserRegisterRequest
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(userRequest: UserRegisterRequest): Flow<Resource<RegisterResponse>>
}