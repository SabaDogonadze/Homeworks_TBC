package com.example.tbchomework18.data.paging

import android.accounts.NetworkErrorException
import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.tbchomework18.data.get_user.GetUsersDataService
import com.example.tbchomework18.data.remote.UserModel
import kotlinx.coroutines.delay
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class UserDataPagingSource @Inject constructor(private val getUserService: GetUsersDataService) :
    PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { state.closestItemToPosition(it)?.id }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            delay(2000) // to see bottom loader , just for testing
            val currentPage = params.key ?: 1
            val response = getUserService.getUsersData(currentPage)

            if (currentPage == 1) { // test if State is Error, how it works.
                throw IOException(" network error")
            }


            d("12345", "Fetched data: ${response.body()}")
            val data = response.body()
            if (response.isSuccessful && data != null) {
                val responseData = data.data
                val nextPage = if (currentPage < data.totalPages) currentPage + 1 else null
                val prevPage = if (currentPage > 1) currentPage - 1 else null
                LoadResult.Page(
                    data = responseData, prevKey = prevPage, nextKey = nextPage
                )
            } else {
                LoadResult.Error(NetworkErrorException("Unsuccessful response or data is null")) // not best practise, get context somehow
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
