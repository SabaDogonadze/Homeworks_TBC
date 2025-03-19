package com.example.tbchomework27.di

import com.example.tbchomework27.domain.search.SearchVehicleRepository
import com.example.tbchomework27.domain.usecase.GetSearchVehiclesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetSearchVehicleUseCase(searchVehicleRepository: SearchVehicleRepository): GetSearchVehiclesUseCase {
        return GetSearchVehiclesUseCase(searchVehicleRepository)
    }
}