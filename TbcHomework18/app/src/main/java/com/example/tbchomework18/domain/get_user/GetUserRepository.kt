package com.example.tbchomework18.domain.get_user

import com.example.tbchomework18.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    suspend fun getUsersData(): Flow<Resource<UsersDataResponse>>
}