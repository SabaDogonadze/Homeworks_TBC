package com.example.tbchomework18.domain.log_in

import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.data.remote.register.UserLogInRequest
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    suspend fun logIn(userLoginRequest: UserLogInRequest,rememberMe:Boolean):Flow<Resource<LogInResponse>>
}