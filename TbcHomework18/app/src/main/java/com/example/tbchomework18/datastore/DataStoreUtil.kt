package com.example.tbchomework18.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tbchomework18.app.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object DataStoreUtil {
    val EMAIL = stringPreferencesKey("email")

    suspend fun saveEmailAndSession(email:String) {
        App.application.applicationContext.dataStore.edit { settings ->
            settings[EMAIL] = email
            Log.d("inDadaStore", "Email and session state saved in DataStore: $email")
        }
    }

    fun readEmail():Flow<String> = App.application.applicationContext.dataStore.data
        .map { preferences ->
            val email = preferences[EMAIL] ?: ""
            Log.d("inDadaStore", "Email read from DataStore: $email")
            email
        }

    suspend fun clearSession() {
        Log.d("inDadaStore", "clearSession invoked")
        App.application.dataStore.edit { settings ->
            settings[EMAIL] = ""
            Log.d("inDadaStore", "Session cleared in DataStore")
        }
    }


}