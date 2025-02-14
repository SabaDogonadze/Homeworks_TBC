package com.example.tbcclasswork7.domain.get_statistics_items

import com.example.tbcclasswork7.data.common.Resource
import kotlinx.coroutines.flow.Flow


interface StatisticsRepository {
    suspend fun getItems(): Flow<Resource<List<ItemsDataResponse>>>
}





