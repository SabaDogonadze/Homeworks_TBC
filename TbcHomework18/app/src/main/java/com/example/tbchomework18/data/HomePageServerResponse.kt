package com.example.tbchomework18.data

import com.squareup.moshi.Json

data class HomePageServerResponse(
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
    val support:Support
)

data class Support(
    @Json(name ="url")
    val url:String,
    @Json(name ="text")
    val text:String
)
