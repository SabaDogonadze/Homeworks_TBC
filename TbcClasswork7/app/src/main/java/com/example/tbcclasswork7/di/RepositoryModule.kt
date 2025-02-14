package com.example.tbcclasswork7.di

import com.example.tbcclasswork7.data.get_statistics_items.GetItemsService
import com.example.tbcclasswork7.data.get_statistics_items.StatisticsRepositoryImpl
import com.example.tbcclasswork7.domain.get_statistics_items.StatisticsRepository
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
    fun provideStatisticsRepository(getItemsService: GetItemsService): StatisticsRepository {
        return StatisticsRepositoryImpl(getItemsService)
    }

}