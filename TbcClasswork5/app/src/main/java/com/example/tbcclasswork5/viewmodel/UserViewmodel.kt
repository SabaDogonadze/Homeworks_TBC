package com.example.tbcclasswork5.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcclasswork5.common.Resource
import com.example.tbcclasswork5.model.ServerResponse
import com.example.tbcclasswork5.network.Network
import com.example.tbcclasswork5.room.User
import com.example.tbcclasswork5.room.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.tbcclasswork5.room.toRoomUser


class UserViewmodel(private val repository: UserRepository):ViewModel() {

    private val _userDataResponseFlow = MutableStateFlow<Resource<ServerResponse>?>(null)
    val userDataResponseFlow : StateFlow<Resource<ServerResponse>?> get() = _userDataResponseFlow

     fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userDataResponseFlow.value = Resource.Loading(load = true)
            try {
                val serverResponse = Network.networkApiService().getUsersData()
                Log.d("HomeFragment", "API Response: ${serverResponse.body()}")
                if (serverResponse.isSuccessful) {
                    Log.d("HomeFragment", "API Response: ${serverResponse.body()}")
                    val users = serverResponse.body()?.users ?: emptyList()
                    users.map {
                        Log.d("userviewmodel", " user: ${it.firstName}")
                        it.toRoomUser()
                    }.forEach {
                        Log.d("userviewmodel", "instert user: ${it.firstName}")
                        repository.insert(it)
                    }
                    _userDataResponseFlow.value = Resource.Success(serverResponse.body())
                } else {
                    _userDataResponseFlow.value = Resource.Error(errorMessage = serverResponse.errorBody()?.string() ?: "")
                }
            } catch (e: Exception) {
                _userDataResponseFlow.value = Resource.Error(errorMessage = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    val allUsers: StateFlow<List<User>> = repository.getAllUsersFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

 /*   fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }*/
/*
    fun insertUserWithPermissions(user: User, permissions: List<String>) = viewModelScope.launch {
        repository.insertUserWithPermissions(user, permissions)
    }

    fun updateUserPermissions(userId: Int, permissions: List<String>) = viewModelScope.launch {
        repository.updateUserPermissions(userId, permissions)
    }

    fun getPermissionsForUser(userId: Int) = repository.getPermissionsForUser(userId)*/

}

