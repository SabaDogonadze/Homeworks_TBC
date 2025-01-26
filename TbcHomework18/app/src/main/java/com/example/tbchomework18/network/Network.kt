package com.example.tbchomework18.network

import com.example.tbchomework18.service.NetworkService
import com.example.tbchomework18.service.UsersService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    private const val BASE_URL =  "https://reqres.in/api/"
   private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    fun networkService() = retrofit.create(NetworkService::class.java)
    fun usersService() = retrofit.create(UsersService::class.java)
}