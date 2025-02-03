package com.example.tbcclasswork5.model

import com.squareup.moshi.Json


data class ServerResponse(
    @Json(name = "status")
    val status: Boolean,
    @Json(name = "additional_data")
    val additionalData: Any?,
    @Json(name = "options")
    val options: Any?,
    @Json(name = "permissions")
    val permissions: List<String?>,
    @Json(name = "users")
    val users: List<User>
)

data class User(
    @Json(name = "id")
    val id: Int,
    @Json(name = "avatar")
    val avatar: String?,
    @Json(name = "first_name")
    val firstName: String,
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "about")
    val about: String?,
    @Json(name = "activation_status")
    val activationStatus: Double
)
