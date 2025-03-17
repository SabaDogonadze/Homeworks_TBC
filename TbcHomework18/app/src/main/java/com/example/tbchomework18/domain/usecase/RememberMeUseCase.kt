package com.example.tbchomework18.domain.usecase

import androidx.datastore.preferences.core.Preferences
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReadRememberMeUseCase {
    suspend operator fun invoke(key: Preferences.Key<Boolean>, defaultValue: Boolean): Flow<Boolean>
}

class ReadRememberMeUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ReadRememberMeUseCase {
    override suspend operator fun invoke(key: Preferences.Key<Boolean>, defaultValue: Boolean): Flow<Boolean> {
        return dataStoreRepository.readSession(key, defaultValue)
    }
}