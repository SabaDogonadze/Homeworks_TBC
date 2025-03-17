package com.example.tbchomework18.domain.datastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository { // change name to manager
   suspend fun <T> saveSessionWithEmail(key: Preferences.Key<T>, value: T)
   fun <T> readSession(key: Preferences.Key<T>, defaultValue: T): Flow<T>
   suspend fun <T> clearSession(key: Preferences.Key<T>)
}