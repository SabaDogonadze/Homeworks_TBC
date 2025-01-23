package com.example.tbchomework18.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.datastore.DataStoreUtil
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _userSession = MutableSharedFlow<Boolean>()
    val userSession: SharedFlow<Boolean> get() = _userSession

    fun readSession() {
        viewModelScope.launch {
            DataStoreUtil.readEmail().collect {
                if (it.isEmpty()) {
                    _userSession.emit(false)
                }else {
                    _userSession.emit(true)
                }
            }
        }
    }

    fun clearSession() {
        Log.d("12345", "clearSession called")
        viewModelScope.launch {
            DataStoreUtil.clearSession()
            _userSession.emit(false)
            Log.d("12345", " clearSession updated to false")
        }
    }
}