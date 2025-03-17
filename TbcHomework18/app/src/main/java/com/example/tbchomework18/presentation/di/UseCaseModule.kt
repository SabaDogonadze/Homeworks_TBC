package com.example.tbchomework18.presentation.di

import com.example.tbchomework18.domain.datastore.DataStoreRepository
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.domain.log_in.LogInRepository
import com.example.tbchomework18.domain.register.RegisterRepository
import com.example.tbchomework18.domain.usecase.ClearSessionUseCase
import com.example.tbchomework18.domain.usecase.ClearSessionUseCaseImpl
import com.example.tbchomework18.domain.usecase.GetUserUseCase
import com.example.tbchomework18.domain.usecase.GetUserUseCaseImpl
import com.example.tbchomework18.domain.usecase.LogInUseCase
import com.example.tbchomework18.domain.usecase.LogInUseCaseImpl
import com.example.tbchomework18.domain.usecase.ReadEmailUseCase
import com.example.tbchomework18.domain.usecase.ReadEmailUseCaseImpl
import com.example.tbchomework18.domain.usecase.ReadRememberMeUseCase
import com.example.tbchomework18.domain.usecase.ReadRememberMeUseCaseImpl
import com.example.tbchomework18.domain.usecase.RegisterUseCase
import com.example.tbchomework18.domain.usecase.RegisterUseCaseImpl
import com.example.tbchomework18.domain.usecase.SaveSessionUseCase
import com.example.tbchomework18.domain.usecase.SaveSessionUseCaseImpl
import com.example.tbchomework18.domain.usecase.validation.ValidateEmailUseCase
import com.example.tbchomework18.domain.usecase.validation.ValidateEmailUseCaseImpl
import com.example.tbchomework18.domain.usecase.validation.ValidateRegisterInputsUseCase
import com.example.tbchomework18.domain.usecase.validation.ValidateRegisterInputsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLogInUseCase(logInRepository: LogInRepository): LogInUseCase {
        return LogInUseCaseImpl(logInRepository)
    }

    @Singleton
    @Provides
    fun provideRegisterUseCase(registerRepository: RegisterRepository): RegisterUseCase {
        return RegisterUseCaseImpl(registerRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserUseCase(getUserRepository: GetUserRepository): GetUserUseCase {
        return GetUserUseCaseImpl(getUserRepository)
    }

    @Singleton
    @Provides
    fun clearSessionUseCase(dataStoreRepository: DataStoreRepository): ClearSessionUseCase {
        return ClearSessionUseCaseImpl(dataStoreRepository)
    }

    @Singleton
    @Provides
    fun readEmailUseCase(dataStoreRepository: DataStoreRepository): ReadEmailUseCase {
        return ReadEmailUseCaseImpl(dataStoreRepository)
    }

    @Singleton
    @Provides
    fun saveSessionUseCase(dataStoreRepository: DataStoreRepository): SaveSessionUseCase {
        return SaveSessionUseCaseImpl(dataStoreRepository)
    }

    @Singleton
    @Provides
    fun validationEmailUseCase(): ValidateEmailUseCase {
        return ValidateEmailUseCaseImpl()
    }

    @Singleton
    @Provides
    fun validateRegisterInputsUseCase(): ValidateRegisterInputsUseCase {
        return ValidateRegisterInputsUseCaseImpl()
    }
    @Singleton
    @Provides
    fun rememberMeUseCase(dataStoreRepository: DataStoreRepository): ReadRememberMeUseCase {
        return ReadRememberMeUseCaseImpl(dataStoreRepository)
    }

}