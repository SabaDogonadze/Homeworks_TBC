package com.example.tbchomework18.domain.register

import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserRegisterRequest
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(userRequest: UserRegisterRequest): Flow<Resource<RegisterResponse>>
}