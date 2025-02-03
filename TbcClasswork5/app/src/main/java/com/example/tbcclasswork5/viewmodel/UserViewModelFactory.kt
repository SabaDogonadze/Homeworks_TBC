package com.example.tbcclasswork5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tbcclasswork5.room.UserRepository


class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserViewmodel::class.java)) {

            @Suppress("UNCHECKED_CAST")

            return UserViewmodel(repository) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
