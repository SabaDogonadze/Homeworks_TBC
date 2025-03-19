package com.example.tbchomework27.di

import com.example.tbchomework27.BuildConfig
import com.example.tbchomework27.data.remote.search.SearchVehicleService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(logging)
            }
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client:OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    moshi
                )
            ).build()
    }


    @Singleton  // this two are responsible for creating an object
    @Provides
    fun provideSearchVehicleService(retrofit: Retrofit): SearchVehicleService { // function injection
        return retrofit.create(SearchVehicleService::class.java)
    }

}