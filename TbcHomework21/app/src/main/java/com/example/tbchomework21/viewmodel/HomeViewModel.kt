package com.example.tbchomework21.viewmodel

import android.app.Application
import android.util.Log
import android.util.Log.d
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework21.datastore.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val _userName = MutableStateFlow("Loading") // i think this is not good practise to have 3 different flows but for now this is it.
    val userName: StateFlow<String> = _userName

    private val _userLastName = MutableStateFlow("Loading")
    val userLastName: StateFlow<String> = _userLastName

    private val _userEmail = MutableStateFlow("Loading")
    val userEmail: StateFlow<String> = _userEmail


    suspend fun getSavedUserName(application: Application){
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreManager.getSavedUserName(application)
                .collect { name ->
                    _userName.value = name
                    Log.d("ProtoDataStore", "Saved User Name: $name")
                }
        }
    }

    fun saveUserName(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreManager.saveUserName(userName, getApplication())
        }
    }


    suspend fun getSavedUserLastName(application: Application){
        viewModelScope.launch(Dispatchers.IO) {
            d("kkkk" ,"${Thread.currentThread().name}")
            DataStoreManager.getSavedUserLastName(application)
                .collect { name ->
                    _userLastName.value = name
                    Log.d("ProtoDataStore", "Saved User Name: $name")
                }
        }
    }

    fun saveUserLastName(userLastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreManager.saveUserLastName( userLastName , getApplication())
        }
    }


    suspend fun getSavedUserEmail(application: Application){
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreManager.getSavedUserEmail(application)
                .collect { name ->
                    _userEmail.value = name
                    Log.d("ProtoDataStore", "Saved User Name: $name")
                }
        }
    }

    fun saveUserEmail(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            DataStoreManager.saveUserEmail( email , getApplication())
        }
    }

}