package com.example.tbchomework18.data.remote.get_user

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.data.paging.user.MyRemoteMediator
import com.example.tbchomework18.data.local.database.UserDataBase
import com.example.tbchomework18.data.local.entity.UsersEntity
import com.example.tbchomework18.domain.get_user.UserModelDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserRepositoryImpl @Inject constructor(
    private val getUserService: GetUsersDataService,
    private val userDataBase: UserDataBase
) : GetUserRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsersData(): Flow<PagingData<UserModelDomain>> {
        val pagingSourceFactory = { userDataBase.userDao().getUsersPagingSource() }

        return Pager(
            config = PagingConfig(pageSize = 6, enablePlaceholders = false),
            remoteMediator = MyRemoteMediator(getUserService, userDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { userEntity ->
                userEntity.toDomainModel()
            }
        }
    }
}



