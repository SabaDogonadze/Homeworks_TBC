package com.example.tbchomework18.data.remote

data class UserRegisterRequest(
    val email:String,
    val password:String
)

data class UserLogInRequest(
    val email:String,
    val password:String
)
