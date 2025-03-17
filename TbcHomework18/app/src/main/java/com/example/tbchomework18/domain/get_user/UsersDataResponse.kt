package com.example.tbchomework18.domain.get_user

import com.example.tbchomework18.data.remote.get_user.UserModel

data class UsersDataResponse (
    val page : Int,
    val perPage:Int,
    val total :Int,
    val totalPages:Int,
    val data:List<UserModel>,
    val support: Support
)

data class Support(
    val url:String,
    val text:String
)


