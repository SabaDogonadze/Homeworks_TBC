package com.example.tbchomework18.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.local.datastore.SessionTracker
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import com.example.tbchomework18.domain.preference_key.PreferenceKeys
import com.example.tbchomework18.domain.usecase.ReadEmailUseCase
import com.example.tbchomework18.domain.usecase.ReadRememberMeUseCase
import com.example.tbchomework18.domain.usecase.SaveSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val readEmailUseCase: ReadEmailUseCase, private val readRememberMeUseCase: ReadRememberMeUseCase)  : ViewModel() {

    fun readSession() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                readEmailUseCase.invoke(PreferenceKeys.TOKEN, ""),
                readRememberMeUseCase.invoke(PreferenceKeys.REMEMBER_ME, false)
            ) { token, rememberMe ->
                token.isNotEmpty() && rememberMe
            }.collect { session ->
                SessionTracker.emitSessionState(session)
            }
        }
    }
}