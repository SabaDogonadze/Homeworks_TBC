package com.example.tbchomework24.data.home

import com.example.tbchomework24.data.common.ApiHelper
import com.example.tbchomework24.data.common.Resource
import com.example.tbchomework24.domain.HomeRepository
import com.example.tbchomework24.domain.PostDataResponse
import com.example.tbchomework24.domain.StoryDataResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val getPostDataService: GetPostDataService,private val getStoryDataService: GetStoryDataService):HomeRepository {
    override suspend fun getStoryData(): Flow<Resource<List<StoryDataResponse>>> {
       return flow {
          // emit(listOf(Resource.Loading(true))) // ✅ Emit loading state

           val result = ApiHelper.handleHttpRequest(
               apiCall = { getStoryDataService.getStoryData() }, // ✅ API call now returns Response<List<StoryItemDto>>
               mapper = { dtoList -> dtoList.map { it.toDomain() } } // ✅ Convert DTO list to Domain list
           )
           emit(result)
          // emit(list(result)) // ✅ Emit result
       }
    }


    override suspend fun getPostData(): Flow<Resource<List<PostDataResponse>>> {
        return flow {
           // emit(listOf(Resource.Loading(true)))
            val result = ApiHelper.handleHttpRequest(
                apiCall = { getPostDataService.getPostData()},
                mapper = { dtoList -> dtoList.map { it.toDomain() }}
            )
            emit(result)
        }
    }
}