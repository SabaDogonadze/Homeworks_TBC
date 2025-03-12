package com.example.tbchomework18.domain.get_user

import androidx.paging.PagingData
import com.example.tbchomework18.room.UsersEntity
import kotlinx.coroutines.flow.Flow

/*
interface GetUserRepository {
    suspend fun getUsersData(): Flow<Resource<UsersDataResponse>>
}*/

interface GetUserRepository {
     fun getUsersData(): Flow<PagingData<UsersEntity>>
}
