package com.example.tbchomework24.domain

import com.example.tbchomework24.data.common.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getStoryData(): Flow<Resource<List<StoryDataResponse>>>
    suspend fun getPostData(): Flow<Resource<List<PostDataResponse>>>
}