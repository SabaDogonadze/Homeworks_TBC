package com.example.tbchomework18.domain.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
   suspend fun saveEmailAndSession(email:String)
   fun readEmail(): Flow<String>
   suspend fun clearSession()

}