package com.example.tbchomework18.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.tbchomework18.data.get_user.GetUsersDataService
import com.example.tbchomework18.room.UserDataBase
import com.example.tbchomework18.room.UsersEntity

@OptIn(ExperimentalPagingApi::class)
class MyRemoteMediator(
    private val service: GetUsersDataService,
    private val database: UserDataBase
) : RemoteMediator<Int, UsersEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UsersEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id?.plus(1) ?: 1
                }
            }

            val response = service.getUsersData(page)
            val endOfPaginationReached = response.message().isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao().clearAll()
                }
            }

            return MediatorResult.Success(endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}

