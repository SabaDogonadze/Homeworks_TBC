package com.example.tbchomework21.datastore

import android.content.Context
import android.util.Log
import com.example.tbchomework21.UserPreferences
import com.example.tbchomework21.datastore.AppStartupParamsSerializer.appStartUpParamsDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

object DataStoreManager {

    fun getSavedUserName(context: Context): Flow<String> {
        return context.appStartUpParamsDataStore.data
            .map { userInfo -> userInfo.userFirstName.ifEmpty { "No Name" } }
    }

    suspend fun saveUserName(userName:String,context: Context){
        try {
            withContext(Dispatchers.IO) {
                context.appStartUpParamsDataStore.updateData { currentUser: UserPreferences ->
                    currentUser.toBuilder()
                        .setUserFirstName(userName).build()
                }
                Log.d("12345", "Saved user name: $userName")
            }
        } catch(e: Exception) {
            Log.e("Error", "Error writing to proto store: $e")
            throw e
        }
    }

    fun getSavedUserLastName(context: Context): Flow<String> {
        return context.appStartUpParamsDataStore.data
            .map { userInfo -> userInfo.userLastName.ifEmpty { "No Name" } }
    }

    suspend fun saveUserLastName(userLastName:String,context: Context){
        try {
            withContext(Dispatchers.IO) {
                context.appStartUpParamsDataStore.updateData { currentUser: UserPreferences ->
                    currentUser.toBuilder()
                        .setUserLastName(userLastName).build()
                }
            }
        } catch(e: Exception) {
            Log.e("Error", "Error writing to proto store: $e")
            throw e
        }
    }

    fun getSavedUserEmail(context: Context): Flow<String> {
        return context.appStartUpParamsDataStore.data
            .map { userInfo -> userInfo.userEmail.ifEmpty { "No Name" } }
    }

    suspend fun saveUserEmail(email:String,context: Context){
        try {
            withContext(Dispatchers.IO) {
                context.appStartUpParamsDataStore.updateData { currentUser: UserPreferences ->
                    currentUser.toBuilder()
                        .setUserEmail(email).build()
                }
            }
        } catch(e: Exception) {
            Log.e("Error", "Error writing to proto store: $e")
            throw e
        }
    }
}