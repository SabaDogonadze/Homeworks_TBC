package com.example.tbcclasswork7.presentation.statistics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbcclasswork7.data.common.Resource
import com.example.tbcclasswork7.domain.get_statistics_items.ItemsDataResponse
import com.example.tbcclasswork7.domain.get_statistics_items.StatisticsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel@Inject constructor(private val statisticsRepository: StatisticsRepository):ViewModel() {

    private val _itemsDataResponseFlow = MutableStateFlow<Resource<List<ItemsDataResponse>>>(
        Resource.Loading(true))
    val itemsDataResponseFlow: StateFlow<Resource<List<ItemsDataResponse>>> = _itemsDataResponseFlow

    fun getItemsData(){
        viewModelScope.launch(Dispatchers.IO){
             statisticsRepository.getItems().collect {
                when (it) {
                    is Resource.Loading -> {_itemsDataResponseFlow.value = Resource.Loading(it.loading)}
                    is Resource.Success -> {
                        Log.d("StatisticsViewModel", "${it.data}")
                        _itemsDataResponseFlow.value = Resource.Success(dataSuccess = it.dataSuccess!!)}
                    is Resource.Error -> {_itemsDataResponseFlow.value = Resource.Error(it.errorMessage)}
                }
            }
        }
    }
}

