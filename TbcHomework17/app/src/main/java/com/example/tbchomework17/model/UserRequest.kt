package com.example.tbchomework17.model


data class UserLoginRequest(
    val email:String,
    val password : String
)

data class UserRegisterRequest(
    val email:String,
    val password : String
)
