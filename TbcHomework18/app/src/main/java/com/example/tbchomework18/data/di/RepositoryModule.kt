package com.example.tbchomework18.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.tbchomework18.data.remote.get_user.GetUserRepositoryImpl
import com.example.tbchomework18.data.remote.get_user.GetUsersDataService
import com.example.tbchomework18.data.local.datastore.DataStoreRepositoryImpl
import com.example.tbchomework18.data.remote.log_in.LogInRepositoryImpl
import com.example.tbchomework18.data.remote.log_in.LogInService
import com.example.tbchomework18.data.remote.register.RegisterRepositoryImpl
import com.example.tbchomework18.data.remote.register.RegisterService
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.data.local.database.UserDataBase
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
    fun provideLogInRepository(logInService: LogInService,dataStoreRepository: DataStoreRepository):LogInRepository{
        return  LogInRepositoryImpl(logInService,dataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(registerService: RegisterService):RegisterRepository{
        return  RegisterRepositoryImpl(registerService)
    }

    @Singleton
    @Provides
    fun provideGetUserRepository(getUsersDataService: GetUsersDataService, dao: UserDataBase):GetUserRepository{
        return  GetUserRepositoryImpl(getUsersDataService,dao)
    }


    @Singleton
    @Provides
    fun provideDataStore(dataStore: DataStore<Preferences>): DataStoreRepository {
        return  DataStoreRepositoryImpl(dataStore)
    }
}