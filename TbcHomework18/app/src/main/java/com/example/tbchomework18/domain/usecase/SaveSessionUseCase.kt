package com.example.tbchomework18.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import javax.inject.Inject

interface SaveSessionUseCase {
    suspend operator fun <T>invoke(key: Preferences.Key<T>, value: T)
}
class SaveSessionUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository):SaveSessionUseCase {
    override suspend fun <T> invoke(key: Preferences.Key<T>, value: T) {
        return dataStoreRepository.saveSessionWithEmail(key,value)
    }
}