package com.example.tbchomework27.data.remote.search

import com.example.tbchomework27.data.common.ApiHelper
import com.example.tbchomework27.domain.common.Resource
import com.example.tbchomework27.domain.search.SearchVehicleModel
import com.example.tbchomework27.domain.search.SearchVehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchVehicleRepositoryImpl @Inject constructor(
    private val searchVehicleService: SearchVehicleService
) : SearchVehicleRepository {
    override fun getSearchVehicles(name: String): Flow<Resource<List<SearchVehicleModel>>> = flow {
        emit(Resource.Loading(load = true))
        val result = ApiHelper.handleHttpRequest {
            searchVehicleService.searchVehicles(name)
        }
        when(result) {
            is Resource.Success -> {
                val domainList = result.dataSuccess.map { it.toDomain() }
                emit(Resource.Success(dataSuccess = domainList))
            }
            is Resource.Error -> emit(Resource.Error(errorMessage = result.errorMessage))
            is Resource.Loading -> emit(Resource.Loading(load = result.loading))
        }
    }
}
