package com.example.tbchomework27.di


import com.example.tbchomework27.data.remote.search.SearchVehicleRepositoryImpl
import com.example.tbchomework27.data.remote.search.SearchVehicleService
import com.example.tbchomework27.domain.search.SearchVehicleRepository
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
    fun provideSearchVehicleRepository(searchVehicleService: SearchVehicleService):SearchVehicleRepository{
        return  SearchVehicleRepositoryImpl(searchVehicleService)
    }

}