package com.example.tbchomework18.domain.get_user

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/*
interface GetUserRepository {
    suspend fun getUsersData(): Flow<Resource<UsersDataResponse>>
}*/

interface GetUserRepository {
     fun getUsersData(): Flow<PagingData<UserModelDomain>>
}
