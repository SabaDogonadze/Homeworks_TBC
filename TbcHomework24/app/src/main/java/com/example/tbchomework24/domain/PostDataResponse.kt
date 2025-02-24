package com.example.tbchomework24.domain

data class PostDataResponse(
    val id: Int,
    val images: List<String>?,// Nullable in case there are no images
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: Owner
)
data class Owner(
    val firstName: String,
    val lastName: String,
    val profile: String?,// Nullable because profile can be null
    val postDate: Long // Epoch timestamp
)

