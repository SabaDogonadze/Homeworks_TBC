package com.example.tbchomework18.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.data.get_user.GetUsersDataService
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.domain.get_user.UsersDataResponse
import com.example.tbchomework18.room.toApiUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val getUserRepository: GetUserRepository):ViewModel() {
    private val _userDataResponseFlow = MutableStateFlow<Resource<UsersDataResponse>?>(null)
    val userDataResponseFlow : StateFlow<Resource<UsersDataResponse>?> = _userDataResponseFlow

    val usersFlow = getUserRepository.getUsersData()
        .map { pagingData ->
            pagingData.map { it.toApiUser() }
        }
        .cachedIn(viewModelScope)

    /*   fun getUserData(){
           viewModelScope.launch(Dispatchers.IO) {
               val response = getUserRepository.getUsersData().collect {
                   when (it) {
                       is Resource.Loading -> {_userDataResponseFlow.value = Resource.Loading(it.loading)}
                       is Resource.Success -> {_userDataResponseFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)}
                       is Resource.Error -> {_userDataResponseFlow.value = Resource.Error(it.errorMessage)}
                   }
               }
           }
       }*/

}