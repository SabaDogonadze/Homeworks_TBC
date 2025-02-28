package com.example.classwork8real.domain

import com.example.classwork8real.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface MapRepository {
    suspend fun getMapLocations(): Flow<Resource<List<MapResponse>>>
}