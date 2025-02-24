package com.example.tbchomework24.data.home

import com.example.tbchomework24.domain.Owner
import com.example.tbchomework24.domain.PostDataResponse

fun PostItemDto.toDomain(): PostDataResponse {
    return PostDataResponse(
        id = this.id,
        images = this.images,
        title = this.title,
        comments = this.comments,
        likes = this.likes,
        shareContent = this.shareContent,
        owner = this.owner.toDomain()
    )
}

fun OwnerDto.toDomain(): Owner {
    return Owner(
        firstName = this.firstName,
        lastName = this.lastName,
        profile = this.profile,
        postDate = this.postDate
    )
}