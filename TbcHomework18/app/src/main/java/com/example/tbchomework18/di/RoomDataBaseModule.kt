package com.example.tbchomework18.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tbchomework18.room.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDataBaseModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context):RoomDatabase{
        return  Room.databaseBuilder(
            context,
            UserDataBase::class.java,
            "user_database"
        ).build()
}
    @Provides
    fun provideUserDao(database: UserDataBase) = database.userDao()
}