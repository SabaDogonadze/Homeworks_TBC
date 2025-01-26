package com.example.tbchomework18.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.datastore.DataStoreUtil
import com.example.tbchomework18.datastore.SessionTracker
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {

    fun readSession() {
        viewModelScope.launch {
            DataStoreUtil.readEmail().collect {
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