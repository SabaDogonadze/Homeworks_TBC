package com.example.tbchomework18.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReadEmailUseCase {
    suspend operator fun <T>invoke(key: Preferences.Key<T>, defaultValue: T): Flow<T>
}
class ReadEmailUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository):ReadEmailUseCase {
    override suspend fun <T> invoke(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStoreRepository.readSession(key,defaultValue)
    }

}