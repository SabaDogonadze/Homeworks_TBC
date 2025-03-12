package com.example.tbchomework18.domain.usecase

import com.example.tbchomework18.domain.datastore.DataStoreRepository
import javax.inject.Inject

interface ClearSessionUseCase {
    suspend operator fun invoke()
}
class ClearSessionUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository):ClearSessionUseCase {
    override suspend fun invoke() {
       return invoke()
    }

}