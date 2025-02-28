package com.example.classwork8real.di

import com.example.classwork8real.data.GetMapLocationService
import com.example.classwork8real.data.MapRepositoryImpl
import com.example.classwork8real.domain.MapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMapRepository(getMapLocationService: GetMapLocationService):MapRepository{
        return  MapRepositoryImpl(getMapLocationService)
    }
}