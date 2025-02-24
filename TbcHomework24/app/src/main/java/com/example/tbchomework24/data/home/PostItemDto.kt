package com.example.tbchomework24.data.home

import com.squareup.moshi.Json

data class PostItemDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<String>?,// Nullable in case there are no images
    @Json(name = "title")
    val title: String,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "share_content")
    val shareContent: String,
    @Json(name = "owner")
    val owner: OwnerDto
)

data class OwnerDto(
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "profile")
    val profile: String?,// Nullable because profile can be null
    @Json(name = "post_date")
    val postDate: Long // Epoch timestamp
)
