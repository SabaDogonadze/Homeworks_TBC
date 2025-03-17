package com.example.tbchomework18.domain.preference_key

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val EMAIL = stringPreferencesKey("email")
    val TOKEN = stringPreferencesKey("token")
    val REMEMBER_ME = booleanPreferencesKey("remember")
}