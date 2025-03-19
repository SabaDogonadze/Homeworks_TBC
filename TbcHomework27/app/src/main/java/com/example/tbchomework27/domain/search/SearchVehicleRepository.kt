package com.example.tbchomework27.domain.search

import com.example.tbchomework27.domain.common.Resource
import kotlinx.coroutines.flow.Flow

interface SearchVehicleRepository {
    fun getSearchVehicles(name:String): Flow<Resource<List<SearchVehicleModel>>>
}