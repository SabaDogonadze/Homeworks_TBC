package com.example.tbchomework18.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.local.datastore.SessionTracker
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository):ViewModel() {
    fun clearSession() {
        viewModelScope.launch {
            dataStoreRepository.clearSession()  // data store must have i think its own module which is not implemented, so i keep this viewmodel as it is now
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