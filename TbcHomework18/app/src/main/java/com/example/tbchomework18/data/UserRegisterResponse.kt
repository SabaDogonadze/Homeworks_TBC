package com.example.tbchomework18.data

data class UserRegisterResponse(
    val id : Int,
    val token : String
)

data class UserLogInResponse(
    val token : String?
)
