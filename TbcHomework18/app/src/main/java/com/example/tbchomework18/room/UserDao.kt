package com.example.tbchomework18.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUsers(user:List<UsersEntity>)

    @Query("SELECT * FROM user_table")
    fun readAllData(): Flow<List<UsersEntity>>

    @Query("DELETE FROM user_table")
    suspend fun clearAll()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUsersPagingSource(): PagingSource<Int, UsersEntity>

}