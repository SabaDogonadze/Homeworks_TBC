package com.example.tbchomework20.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.tbchomework20.network.Network
import com.example.tbchomework20.paging.UserDataPagingSource

class HomeViewModel:ViewModel() {

    val homeUserDataflow = Pager(PagingConfig(pageSize = 6, enablePlaceholders = false, initialLoadSize = 6,prefetchDistance = 1)) {
        UserDataPagingSource(Network.networkApiService())
    }.flow.cachedIn(viewModelScope) // this ties a flow to a viewModelScope, caches data temporarily in memory and it can be re used and it does not need to be re fetched,( reloaded)

// initialLoadSize - tells how many items should be appear when first request is made ( for example when recycler appears and initialLoadSize is 10 , 10 items will appear
//pageSize - each page how many items are displayed

}

