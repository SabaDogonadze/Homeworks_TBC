package com.example.tbchomework18.data.paging.user

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.tbchomework18.data.remote.get_user.GetUsersDataService
import com.example.tbchomework18.data.local.database.UserDataBase
import com.example.tbchomework18.data.local.entity.UsersEntity


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

                    if (lastItem == null) 1 else (lastItem.id / state.config.pageSize) + 1
                }
            }

            val response = service.getUsersData(page)
            if (!response.isSuccessful) {
                throw Exception("Network error: ${response.code()}")
            }

            val usersResponse = response.body()

                ?: throw Exception("Empty response body")

            val endOfPaginationReached = page >= usersResponse.totalPages

            val usersEntities = usersResponse.data.map { user ->

                UsersEntity(
                    id = user.id,
                    email = user.email,
                    firstName = user.firstName,
                    lastName = user.lastName,
                    avatar = user.avatar
                )
            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.userDao().clearAll()
                }
                database.userDao().addUsers(usersEntities)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }

}



