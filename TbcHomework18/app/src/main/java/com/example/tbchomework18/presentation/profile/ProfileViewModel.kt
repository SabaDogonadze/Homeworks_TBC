package com.example.tbchomework18.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.local.datastore.SessionTracker
import com.example.tbchomework18.domain.preference_key.PreferenceKeys
import com.example.tbchomework18.domain.usecase.ClearSessionUseCase
import com.example.tbchomework18.domain.usecase.ReadEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val clearSessionUseCase: ClearSessionUseCase,private val readEmailUseCase: ReadEmailUseCase):ViewModel() {

    init {
        readEmailFromSession()
    }

    private val _state = MutableStateFlow(ProfileStateUi())
    val state: StateFlow<ProfileStateUi> get() = _state.asStateFlow()

    private val _uiEvents = Channel<OneTimeProfileEvents>()
    val uiEvents get() = _uiEvents.receiveAsFlow()

    fun event(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.LogOutButtonClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    clearSession()
                }
            }
        }
    }

    private fun clearSession() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            clearSessionUseCase.invoke(PreferenceKeys.EMAIL)  // clear token not email only and also change remember me state to false, inject use cases
            SessionTracker.emitSessionState(false)
            _state.update {
                it.copy(isLoading = false)
            }
            _uiEvents.send(OneTimeProfileEvents.NavigateToLogIn)
        }
    }
     private fun readEmailFromSession(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            readEmailUseCase.invoke(PreferenceKeys.EMAIL,"EMAIl")
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }
}
