package com.example.tbchomework27.domain.usecase

import com.example.tbchomework27.domain.common.Resource
import com.example.tbchomework27.domain.search.SearchVehicleModel
import com.example.tbchomework27.domain.search.SearchVehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchVehiclesUseCase(  // this need its abstraction
    private val repository: SearchVehicleRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<SearchVehicleModel>>> {
        return repository.getSearchVehicles(query).map { resource ->
            when (resource) {
                is Resource.Success -> {
                    val vehicles = resource.data ?: emptyList()
                    val flatList = vehicles.flatMap { flatten(it) }
                    val filtered = flatList.filter {
                        it.name.startsWith(query, ignoreCase = true)
                    }.map { vehicle ->
                        vehicle.copy(depth = vehicle.depth.coerceAtMost(4))
                    }
                    Resource.Success(filtered)
                }
                is Resource.Loading -> Resource.Loading(resource.loading)
                is Resource.Error -> Resource.Error(resource.errorMessage)
            }
        }
    }

    private fun flatten(vehicle: SearchVehicleModel, currentDepth: Int = 0): List<SearchVehicleModel> { // recursive function
        val current = vehicle.copy(depth = currentDepth)
        val children = vehicle.children.flatMap { flatten(it, currentDepth + 1) }
        return listOf(current) + children
    }
}