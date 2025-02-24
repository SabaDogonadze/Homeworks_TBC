package com.example.tbchomework24.presentation.fragment.home

import com.example.tbchomework24.domain.PostDataResponse
import com.example.tbchomework24.domain.StoryDataResponse

sealed class HomeItem {
    data class StoryItem(val stories: List<StoryDataResponse>) : HomeItem()
    data class PostItem(val posts: List<PostDataResponse>) : HomeItem()
}