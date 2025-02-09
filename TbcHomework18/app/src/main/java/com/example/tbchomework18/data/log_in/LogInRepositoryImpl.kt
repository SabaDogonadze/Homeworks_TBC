package com.example.tbchomework18.data.log_in

import com.example.tbchomework18.data.common.ApiHelper
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.register.toDomain
import com.example.tbchomework18.data.remote.UserLogInRequest
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.log_in.LogInResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor(private val logInService: LogInService) : LogInRepository {
    override suspend fun logIn(userLoginRequest: UserLogInRequest): Flow<Resource<LogInResponse>> {
       return flow {
           emit(Resource.Loading(true))
           val result = ApiHelper.handleHttpRequest(
               apiCall = { logInService.logIn(userRequest = userLoginRequest) },
               mapper = { dto -> dto.toDomain() }
           )
           emit(result)

         /*  emit(Resource.Loading(true))
           try {
               val userLoginResponse = logInService.logIn(userRequest = userLoginRequest)
               if (userLoginResponse.isSuccessful){
                   emit(Resource.Success(dataSuccess = userLoginResponse.body()!!.toDomain()))
               }else{
                   emit( Resource.Error(errorMessage = userLoginResponse.errorBody()?.string()?:""))
               }
           }catch (e:Exception){
               // here we should implement custom errors
           }*/
       }
    }
}