package com.example.tbchomework18.domain.usecase

import androidx.paging.PagingData
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.domain.get_user.UserModelDomain
import com.example.tbchomework18.domain.get_user.UsersDataResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface GetUserUseCase {
    operator fun invoke() : Flow<PagingData<UserModelDomain>>
}
class GetUserUseCaseImpl @Inject constructor(private val getUserRepository: GetUserRepository):GetUserUseCase {
    override operator fun invoke(): Flow<PagingData<UserModelDomain>> {
        return getUserRepository.getUsersData()
    }
}