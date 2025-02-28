package com.example.classwork8real.data

import com.example.classwork8real.data.common.ApiHelper
import com.example.classwork8real.data.common.Resource
import com.example.classwork8real.domain.MapRepository
import com.example.classwork8real.domain.MapResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(private val getMapLocationService: GetMapLocationService) :
    MapRepository {

    override suspend fun getMapLocations(): Flow<Resource<List<MapResponse>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { getMapLocationService.getLocations() },
                mapper = { dtoList -> dtoList.map { it.toDomain() } }
            )
            emit(result)
        }
    }
}