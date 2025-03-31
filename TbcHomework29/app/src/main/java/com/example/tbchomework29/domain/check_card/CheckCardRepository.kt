package com.example.tbchomework29.domain.check_card

import com.example.tbchomework29.domain.common.Resource
import kotlinx.coroutines.flow.Flow

interface CheckCardRepository {
    fun checkUserCardStatus(accountNumber:String): Flow<Resource<CheckCard>>
}