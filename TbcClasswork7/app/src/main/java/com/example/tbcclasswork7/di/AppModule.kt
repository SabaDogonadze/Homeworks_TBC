package com.example.tbcclasswork7.di

import com.example.tbcclasswork7.data.get_statistics_items.GetItemsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create(
                moshi
            )
        ).build()
    }


    @Singleton  // this two are responsible for creating an object
    @Provides
    fun provideItemsService(retrofit: Retrofit): GetItemsService { // function injection
        return retrofit.create(GetItemsService::class.java)
    }

}