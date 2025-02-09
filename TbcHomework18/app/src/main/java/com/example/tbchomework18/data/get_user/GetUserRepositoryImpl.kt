package com.example.tbchomework18.data.get_user

import com.example.tbchomework18.data.common.ApiHelper
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.log_in.toDomain
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.domain.get_user.UsersDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserRepositoryImpl @Inject constructor(private val getUserService: GetUsersDataService):GetUserRepository {
    override suspend fun getUsersData(): Flow<Resource<UsersDataResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { getUserService.getUsersData() },
                mapper = { dto -> dto.toDomain() }
            )
            emit(result)

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
        }
    }
}