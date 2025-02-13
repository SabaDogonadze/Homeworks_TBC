package com.example.tbchomework18.data.get_user

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.data.paging.MyRemoteMediator
import com.example.tbchomework18.room.UserDataBase
import com.example.tbchomework18.room.UsersEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*
class GetUserRepositoryImpl @Inject constructor(private val getUserService: GetUsersDataService):GetUserRepository {
    override suspend fun getUsersData(): Flow<Resource<UsersDataResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { getUserService.getUsersData() },
                mapper = { dto -> dto.toDomain() }
            )
            emit(result)

         */
/*   emit(Resource.Loading(true))
            try {
                val userDataResponse = getUserService.getUsersData()
                if (userDataResponse.isSuccessful){
                    emit(Resource.Success(dataSuccess = userDataResponse.body()!!.toDomain()))
                }else{
                    emit( Resource.Error(errorMessage = userDataResponse.errorBody()?.string()?:""))
                }
            }catch (e:Exception){
                // here we should implement custom errors
            }*//*

        }
    }
}*/


/*
class GetUserRepositoryImpl @Inject constructor(private val getUserService: GetUsersDataService, private val userDataBase: UserDataBase):GetUserRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getUsersData(): Flow<PagingData<UsersEntity>> {

        val pagingSourceFactory = { userDataBase.userDao().getUsersPagingSource() }

        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MyRemoteMediator(getUserService, userDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow


    }
}
*/
class GetUserRepositoryImpl @Inject constructor(
    private val getUserService: GetUsersDataService,
    private val userDataBase: UserDataBase
) : GetUserRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsersData(): Flow<PagingData<UsersEntity>> {
        val pagingSourceFactory = { userDataBase.userDao().getUsersPagingSource() }

        return Pager(
            config = PagingConfig(pageSize = 6, enablePlaceholders = false),
            remoteMediator = MyRemoteMediator(getUserService, userDataBase),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}


/*
            fun getAllUsersFlow() = userDao.readAllData()

            suspend fun insert(user:UsersEntity){
                userDao.addUser(user)
            }*/


/* emit(result)*/

/*   emit(Resource.Loading(true))
   try {
       val userDataResponse = getUserService.getUsersData()
       if (userDataResponse.isSuccessful){
           emit(Resource.Success(dataSuccess = userDataResponse.body()!!.toDomain()))
       }else{
           emit( Resource.Error(errorMessage = userDataResponse.errorBody()?.string()?:""))
       }
   }catch (e:Exception){
       // here we should implement custom errors
   }*/
