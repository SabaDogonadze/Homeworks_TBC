package com.example.tbchomework29.data.common


import com.example.tbchomework29.domain.common.Resource
import retrofit2.Response

object ApiHelper {
    suspend fun <T> handleHttpRequest(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        val response = apiCall.invoke()
        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(dataSuccess = it)
                } ?: Resource.Error(errorMessage = "Response is null")
            } else {
                Resource.Error(errorMessage = response.errorBody()?.string() ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(errorMessage = e.message ?: "An error occurred")
        }
    }
}