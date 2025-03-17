package com.example.tbchomework18.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import javax.inject.Inject

interface ClearSessionUseCase {
    suspend operator fun <T>invoke(key: Preferences.Key<T>)
}
class ClearSessionUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository):ClearSessionUseCase {
    override suspend fun <T> invoke(key: Preferences.Key<T>) {
       dataStoreRepository.clearSession(key)
    }
}