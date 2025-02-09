package com.example.tbchomework18.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.local.datastore.DataStoreUtil
import com.example.tbchomework18.data.local.datastore.SessionTracker
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModel() {
    fun clearSession() {
        viewModelScope.launch {
            DataStoreUtil.clearSession()  // data store must have i think its own module which is not implemented, so i keep this viewmodel as it is now
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