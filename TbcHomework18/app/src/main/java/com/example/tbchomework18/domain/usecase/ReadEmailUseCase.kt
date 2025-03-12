package com.example.tbchomework18.domain.usecase

import com.example.tbchomework18.domain.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReadEmailUseCase {
    operator fun invoke(): Flow<String>
}
class ReadEmailUseCaseImpl @Inject constructor(private val dataStoreRepository: DataStoreRepository):ReadEmailUseCase {
    override fun invoke(): Flow<String> {
        return dataStoreRepository.readEmail()
    }
}