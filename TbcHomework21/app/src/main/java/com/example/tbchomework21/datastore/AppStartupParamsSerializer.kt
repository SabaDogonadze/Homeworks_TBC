package com.example.tbchomework21.datastore

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.tbchomework21.UserPreferences
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

object AppStartupParamsSerializer:Serializer<UserPreferences> {
    override val defaultValue: UserPreferences
        get() = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences = withContext(Dispatchers.IO){
        try {
            return@withContext UserPreferences.parseFrom(input)
        }catch (exception:InvalidProtocolBufferException){
            throw CorruptionException("cannot read proto",exception)
        }
    }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) = withContext(Dispatchers.IO){t.writeTo(output)}

    val Context.appStartUpParamsDataStore:DataStore<UserPreferences> by dataStore(
        fileName = "user_params.pb",
        serializer = AppStartupParamsSerializer
    )
}