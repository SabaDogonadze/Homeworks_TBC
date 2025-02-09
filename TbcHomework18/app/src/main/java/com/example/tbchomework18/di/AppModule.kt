package com.example.tbchomework18.di

import com.example.tbchomework18.data.get_user.GetUsersDataService
import com.example.tbchomework18.data.log_in.LogInService
import com.example.tbchomework18.data.register.RegisterService
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
    private const val BASE_URL = "https://reqres.in/api/"

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
    fun provideLoginService(retrofit: Retrofit):LogInService{ // function injection
        return retrofit.create(LogInService::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterService(retrofit: Retrofit):RegisterService{
        return retrofit.create(RegisterService::class.java)
    }


    @Singleton
    @Provides
    fun provideGetUsersDataService(retrofit: Retrofit):GetUsersDataService{
        return retrofit.create(GetUsersDataService::class.java)
    }
}

