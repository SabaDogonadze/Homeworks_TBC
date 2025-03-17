package com.example.tbchomework18.data.di

import com.example.tbchomework18.data.remote.get_user.GetUsersDataService
import com.example.tbchomework18.data.remote.log_in.LogInService
import com.example.tbchomework18.data.remote.register.RegisterService
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
        return Retrofit.Builder().baseUrl(com.example.tbchomework18.BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(
            MoshiConverterFactory.create(
                moshi
            )
        ).build()
    }


    @Singleton  // this two are responsible for creating an object
    @Provides
    fun provideLoginService(retrofit: Retrofit): LogInService { // function injection
        return retrofit.create(LogInService::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterService(retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }


    @Singleton
    @Provides
    fun provideGetUsersDataService(retrofit: Retrofit): GetUsersDataService {
        return retrofit.create(GetUsersDataService::class.java)
    }
}

