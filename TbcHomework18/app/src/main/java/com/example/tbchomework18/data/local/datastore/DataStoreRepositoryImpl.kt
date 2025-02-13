package com.example.tbchomework18.data.local.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tbchomework18.domain.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>):DataStoreRepository {
    val EMAIL = stringPreferencesKey("email")

    override suspend fun saveEmailAndSession(email:String) {
       dataStore.edit { settings ->
            settings[EMAIL] = email
            Log.d("inDadaStore", "Email and session state saved in DataStore: $email")
        }
    }

    override fun readEmail():Flow<String> = dataStore.data
        .map { preferences ->
            val email = preferences[EMAIL] ?: ""
            Log.d("inDadaStore", "Email read from DataStore: $email")
            email
        }

    override suspend fun clearSession() {
        Log.d("inDadaStore", "clearSession invoked")
        dataStore.edit { settings ->
            settings[EMAIL] = ""
            Log.d("inDadaStore", "Session cleared in DataStore")
        }
    }


}