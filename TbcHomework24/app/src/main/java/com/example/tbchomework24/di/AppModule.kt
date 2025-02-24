package com.example.tbchomework24.di

import com.example.tbchomework24.data.home.GetPostDataService
import com.example.tbchomework24.data.home.GetStoryDataService
import com.google.android.filament.BuildConfig
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
    @Singleton
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

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder().baseUrl(com.example.tbchomework24.BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    moshi
                )
            ).build()
    }


    @Singleton
    @Provides
    fun provideGetPostDataService(retrofit: Retrofit):GetPostDataService{
        return retrofit.create(GetPostDataService::class.java)
    }

    @Singleton
    @Provides
    fun provideGetStoryDataService(retrofit: Retrofit):GetStoryDataService{
        return retrofit.create(GetStoryDataService::class.java)
    }
}