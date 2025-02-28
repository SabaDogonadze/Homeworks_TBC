package com.example.classwork8real.presentation.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.classwork8real.data.common.Resource
import com.example.classwork8real.domain.MapRepository
import com.example.classwork8real.domain.MapResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val mapRepository: MapRepository):ViewModel() {

    private val _locationFlow = MutableStateFlow<Resource<List<MapResponse>>?>(null)
    val locationFlow: StateFlow<Resource<List<MapResponse>>?> = _locationFlow

    fun getLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mapRepository.getMapLocations().collect {
                when (it) {
                    is Resource.Loading -> {
                        _locationFlow.value = Resource.Loading(it.loading)
                    }

                    is Resource.Success -> {
                        _locationFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)
                    }

                    is Resource.Error -> {
                        _locationFlow.value = Resource.Error(it.errorMessage)
                    }
                }
            }
        }
    }
}