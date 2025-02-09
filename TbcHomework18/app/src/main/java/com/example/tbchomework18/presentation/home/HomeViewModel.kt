package com.example.tbchomework18.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbchomework18.data.common.Resource
import com.example.tbchomework18.domain.get_user.GetUserRepository
import com.example.tbchomework18.domain.get_user.UsersDataResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val getUserRepository: GetUserRepository):ViewModel() {
    private val _userDataResponseFlow = MutableStateFlow<Resource<UsersDataResponse>?>(null)
    val userDataResponseFlow : StateFlow<Resource<UsersDataResponse>?> = _userDataResponseFlow

    fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = getUserRepository.getUsersData().collect {
                when (it) {
                    is Resource.Loading -> {_userDataResponseFlow.value = Resource.Loading(it.loading)}
                    is Resource.Success -> {_userDataResponseFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)}
                    is Resource.Error -> {_userDataResponseFlow.value = Resource.Error(it.errorMessage)}
                }
            }
        }
    }

}