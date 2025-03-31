package com.example.tbchomework29.di

import com.example.tbchomework29.data.remote.check_card.CheckCardRepositoryImpl
import com.example.tbchomework29.data.remote.check_card.CheckCardService
import com.example.tbchomework29.data.remote.user_cards.UserCardsRepositoryImpl
import com.example.tbchomework29.data.remote.user_cards.UserCardsService
import com.example.tbchomework29.domain.check_card.CheckCardRepository
import com.example.tbchomework29.domain.user_cards.UserCardsRepository
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
    fun provideUserCardsRepository(userCardsService: UserCardsService): UserCardsRepository {
        return UserCardsRepositoryImpl(userCardsService)
    }

    @Singleton
    @Provides
    fun provideCheckCardRepository(checkCardService: CheckCardService): CheckCardRepository {
        return CheckCardRepositoryImpl(checkCardService)
    }


}