package com.example.tbchomework24.di

import com.example.tbchomework24.data.home.GetPostDataService
import com.example.tbchomework24.data.home.GetStoryDataService
import com.example.tbchomework24.data.home.HomeRepositoryImpl
import com.example.tbchomework24.domain.HomeRepository
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
    fun provideLogInRepository(getPostDataService: GetPostDataService, getStoryDataService: GetStoryDataService):HomeRepository{
        return  HomeRepositoryImpl(getPostDataService,getStoryDataService)
    }
}