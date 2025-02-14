package com.example.tbcclasswork7.data.get_statistics_items

import android.util.Log
import com.example.tbcclasswork7.data.common.ApiHelper
import com.example.tbcclasswork7.data.common.Resource
import com.example.tbcclasswork7.domain.get_statistics_items.ItemsDataResponse
import com.example.tbcclasswork7.domain.get_statistics_items.StatisticsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StatisticsRepositoryImpl @Inject constructor(private val getItemsService: GetItemsService):
    StatisticsRepository {
    override suspend fun getItems(): Flow<Resource<List<ItemsDataResponse>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val result = ApiHelper.handleHttpRequest(
                    apiCall = { getItemsService.getItemsData() },
                    mapper = { dtoList -> dtoList.map { it.toDomain() } }
                )
                Log.d("StatisticsRepository", "Data fetched: $result")
                emit(result)
            } catch (e: Exception) {
                Log.e("StatisticsRepository", "Error fetching data: ${e.localizedMessage}") // Log the error
                emit(Resource.Error<List<ItemsDataResponse>>(e.localizedMessage ?: "Unknown error"))
            }
        }

    }
}
