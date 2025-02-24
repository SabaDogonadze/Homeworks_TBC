package com.example.tbchomework24.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework24.data.common.Resource
import com.example.tbchomework24.domain.HomeRepository
import com.example.tbchomework24.domain.PostDataResponse
import com.example.tbchomework24.domain.StoryDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
 
     //private val _storyResponseFlow = MutableStateFlow<Resource<StoryDataResponse>?>(null)
   // val storyResponseFlow: StateFlow<Resource<StoryDataResponse>?> = _storyResponseFlow

   // private val _postResponseFlow = MutableStateFlow<Resource<PostDataResponse>?>(null)
//val postResponseFlow: StateFlow<Resource<PostDataResponse>?> = _postResponseFlow

    private val _storyResponseFlow = MutableStateFlow<Resource<List<StoryDataResponse>>?>(null)
    val storyResponseFlow: StateFlow<Resource<List<StoryDataResponse>>?> = _storyResponseFlow

    private val _postResponseFlow = MutableStateFlow<Resource<List<PostDataResponse>>?>(null)
    val postResponseFlow: StateFlow<Resource<List<PostDataResponse>>?> = _postResponseFlow

    val homeItemsFlow: Flow<List<HomeItem>> = combine(
        storyResponseFlow,
        postResponseFlow
    ) { storyResource, postResource ->
        if (storyResource is Resource.Success && postResource is Resource.Success) {
            val stories = storyResource.dataSuccess ?: emptyList()
            val posts = postResource.dataSuccess ?: emptyList()
            listOf(
                HomeItem.StoryItem(stories),
                HomeItem.PostItem(posts)
            )
        } else {
            emptyList()
        }
    }

    fun getStory() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getStoryData().collect {
                when (it) {
                    is Resource.Loading -> {
                        _storyResponseFlow.value = Resource.Loading(it.loading)
                    }

                    is Resource.Success -> {
                        _storyResponseFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)
                    }

                    is Resource.Error -> {
                        _storyResponseFlow.value = Resource.Error(it.errorMessage)
                    }
                }
            }
        }
    }

    fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getPostData().collect {
                when (it) {
                    is Resource.Loading -> {
                        _postResponseFlow.value = Resource.Loading(it.loading)
                    }

                    is Resource.Success -> {
                        _postResponseFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)
                    }

                    is Resource.Error -> {
                        _postResponseFlow.value = Resource.Error(it.errorMessage)
                    }
                }
            }
        }
    }
}