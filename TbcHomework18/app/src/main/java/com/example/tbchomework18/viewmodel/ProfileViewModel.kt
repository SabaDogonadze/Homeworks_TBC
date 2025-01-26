package com.example.tbchomework18.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.datastore.DataStoreUtil
import com.example.tbchomework18.datastore.SessionTracker
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModel() {
    fun clearSession() {
        viewModelScope.launch {
            DataStoreUtil.clearSession()
            SessionTracker.emitSessionState(false)
        }
    }
}


/*   private val _userSession = MutableSharedFlow<Boolean>()
    val userSession: SharedFlow<Boolean> get() = _userSession*/

/* fun clearSession() {
       Log.d("12345", "clearSession called")
       viewModelScope.launch {
           DataStoreUtil.clearSession()
           Log.d("12345", " clearSession updated to false")
       }
   }*/