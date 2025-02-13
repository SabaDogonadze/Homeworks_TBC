package com.example.tbchomework18.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.local.datastore.SessionTracker
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository)  : ViewModel() {

    fun readSession() {
        viewModelScope.launch { // data store must have i think its own module which is not implemented, so i keep this viewmodel as it is now
            dataStoreRepository.readEmail().collect {
                SessionTracker.emitSessionState(it.isNotEmpty())
            }
        }
    }
}


/* private val _userSession = MutableSharedFlow<Boolean>()
   val userSession: SharedFlow<Boolean> get() = _userSession*/


/*  fun readSession() {
        viewModelScope.launch {
            DataStoreUtil.readEmail().collect {
                if (it.isEmpty()) {
                    _userSession.emit(false)
                }else {
                    _userSession.emit(true)
                }
            }
        }
    }*/

/* fun clearSession() {
     Log.d("12345", "clearSession called")
     viewModelScope.launch {
         DataStoreUtil.clearSession()
         _userSession.emit(false)
         Log.d("12345", " clearSession updated to false")
     }
 }*/