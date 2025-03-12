package com.example.tbchomework18.domain.usecase

import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.remote.UserRegisterRequest
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.domain.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SaveSessionUseCase {
    suspend operator fun invoke(email:String)
}
class SaveSessionUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository):SaveSessionUseCase {
    override suspend fun invoke(email: String) {
       return dataStoreRepository.saveEmailAndSession(email)
    }
}