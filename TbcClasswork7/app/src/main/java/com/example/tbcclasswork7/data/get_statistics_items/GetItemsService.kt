package com.example.tbcclasswork7.data.get_statistics_items

import retrofit2.Response
import retrofit2.http.GET

interface GetItemsService {
    @GET("6dffd14a-836f-4566-b024-bd41ace3a874")
    suspend fun getItemsData() : Response<List<ItemsDataResponseDto>>
}

/*
https://run.mocky.io/v3/6dc7f56b-8a07-4686-9d15-9d5f780b4549*/
