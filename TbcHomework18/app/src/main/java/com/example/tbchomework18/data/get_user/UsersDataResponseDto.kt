package com.example.tbchomework18.data.get_user

import com.example.tbchomework18.data.remote.UserModel
import com.squareup.moshi.Json

data class UsersDataResponseDto(
    @Json(name ="page")
    val page : Int,
    @Json(name ="per_page")
    val perPage:Int,
    @Json(name ="total")
    val total :Int,
    @Json(name ="total_pages")
    val totalPages:Int,
    @Json(name ="data")
    val data:List<UserModel>,
    @Json(name ="support")
    val support: SupportDto
)

data class SupportDto(
    @Json(name ="url")
    val url:String,
    @Json(name ="text")
    val text:String
)

