package com.example.tbchomework18.data.register

import com.example.tbchomework18.data.common.ApiHelper
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserRegisterRequest
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.domain.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val registerService: RegisterService) :
    RegisterRepository {
    override suspend fun register(userRequest: UserRegisterRequest): Flow<Resource<RegisterResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { registerService.register(userRequest = userRequest) },
                mapper = { dto -> dto.toDomain() }
            )
            emit(result)

            /*  try {
                  val userRegisterResponse = registerService.register(userRequest = userRequest)
                  if (userRegisterResponse.isSuccessful){
                      emit(Resource.Success(dataSuccess = userRegisterResponse.body()!!.toDomain()))
                  }else{
                      emit( Resource.Error(errorMessage = userRegisterResponse.errorBody()?.string()?:""))
                  }
              }catch (e:Exception){
                  // here we should implement custom errors
              }*/
        }
    }
}

