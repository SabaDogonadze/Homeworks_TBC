package com.example.tbchomework18.data.remote.log_in

import com.example.tbchomework18.data.common.ApiHelper
import com.example.tbchomework18.domain.common.Resource
import com.example.tbchomework18.data.remote.register.UserLogInRequest
import com.example.tbchomework18.domain.common.mapResource
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.log_in.LogInResponse
import com.example.tbchomework18.domain.preference_key.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogInRepositoryImpl @Inject constructor( // inject datastore repository which should be a manager not repository
    private val logInService: LogInService,
    private val dataStoreRepository: DataStoreRepository
) : LogInRepository {
    override suspend fun logIn(userLoginRequest: UserLogInRequest,rememberMe:Boolean): Flow<Resource<LogInResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { logInService.logIn(userRequest = userLoginRequest) }
            )
            if (result is Resource.Success) {
                result.data?.token?.let { token ->
                    dataStoreRepository.saveSessionWithEmail(PreferenceKeys.TOKEN, token) // naming is bad, should change something more generic
                    dataStoreRepository.saveSessionWithEmail(PreferenceKeys.REMEMBER_ME, rememberMe)
                }
            }
            emit(result)
        }.mapResource { logInResponse ->
            logInResponse.toDomain()
        }
    }
}
