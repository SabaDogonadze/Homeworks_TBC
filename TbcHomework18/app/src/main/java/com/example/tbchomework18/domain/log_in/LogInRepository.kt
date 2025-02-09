package com.example.tbchomework18.domain.log_in

import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserLogInRequest
import kotlinx.coroutines.flow.Flow

interface LogInRepository {
    suspend fun logIn(userLoginRequest: UserLogInRequest):Flow<Resource<LogInResponse>>
}