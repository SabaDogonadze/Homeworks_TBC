package com.example.tbchomework18.domain.usecase

import androidx.paging.PagingData
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.room.UsersEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetUserUseCase {
    operator fun invoke() : Flow<PagingData<UsersEntity>>
}
class GetUserUseCaseImpl @Inject constructor(private val getUserRepository: GetUserRepository):GetUserUseCase {
    override operator fun invoke(): Flow<PagingData<UsersEntity>> {
        return getUserRepository.getUsersData()
    }
}