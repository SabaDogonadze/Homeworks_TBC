package com.example.tbchomework29.di

import com.example.tbchomework29.domain.check_card.CheckCardRepository
import com.example.tbchomework29.domain.usecase.CheckAccountStatusCoordinatorUseCase
import com.example.tbchomework29.domain.usecase.CheckAccountStatusCoordinatorUseCaseImpl
import com.example.tbchomework29.domain.usecase.CheckUserCardStatusUseCase
import com.example.tbchomework29.domain.usecase.CheckUserCardStatusUseCaseImpl
import com.example.tbchomework29.domain.usecase.GetUserCardsUseCase
import com.example.tbchomework29.domain.usecase.GetUserCardsUseCaseImpl
import com.example.tbchomework29.domain.usecase.ValidateAccountNumberUseCase
import com.example.tbchomework29.domain.usecase.ValidateAccountNumberUseCaseImpl
import com.example.tbchomework29.domain.usecase.ValidatePersonalNumberUseCase
import com.example.tbchomework29.domain.usecase.ValidatePersonalNumberUseCaseImpl
import com.example.tbchomework29.domain.usecase.ValidatePhoneNumberUseCase
import com.example.tbchomework29.domain.usecase.ValidatePhoneNumberUseCaseImpl
import com.example.tbchomework29.domain.user_cards.UserCardsRepository
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
    fun provideUserCardCase(userCardsRepository: UserCardsRepository): GetUserCardsUseCase {
        return GetUserCardsUseCaseImpl(userCardsRepository)
    }

    @Singleton
    @Provides
    fun provideCheckUserCardStatusUseCase(checkCardRepository: CheckCardRepository): CheckUserCardStatusUseCase {
        return CheckUserCardStatusUseCaseImpl(checkCardRepository)
    }

    @Singleton
    @Provides
    fun provideValidatePersonalNumberUseCase(): ValidatePersonalNumberUseCase {
        return ValidatePersonalNumberUseCaseImpl()
    }

    @Singleton
    @Provides
    fun provideValidatePhoneNumberUseCase(): ValidatePhoneNumberUseCase {
        return ValidatePhoneNumberUseCaseImpl()
    }

    @Singleton
    @Provides
    fun provideValidateAccountNumberUseCase(): ValidateAccountNumberUseCase {
        return ValidateAccountNumberUseCaseImpl()
    }

    @Singleton
    @Provides
    fun provideCheckAccountStatusCoordinatorUseCase(validateAccountNumberUseCase: ValidateAccountNumberUseCase,checkUserCardStatusUseCase: CheckUserCardStatusUseCase): CheckAccountStatusCoordinatorUseCase {
        return CheckAccountStatusCoordinatorUseCaseImpl(validateAccountNumberUseCase,checkUserCardStatusUseCase)
    }

}