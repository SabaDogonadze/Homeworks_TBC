package com.example.tbcclasswork5.room

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
    suspend fun addUser(user:User)

    @Query("SELECT * FROM user_table")
    fun readAllData(): Flow<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}

@Dao
interface PermissionDao {
    @Insert
    suspend fun addPermission(permission: Permission)

    @Query("SELECT * FROM permissions WHERE name = :name LIMIT 1")
    suspend fun getPermissionByName(name: String): Permission?
}

@Dao
interface UserPermissionDao {
    @Insert
    suspend fun addUserPermission(userPermission: UserPermission)

    @Query("DELETE FROM UserPermission WHERE userId = :userId")
    suspend fun deleteUserPermissionsByUserId(userId: Int)

    @Query("SELECT * FROM UserPermission WHERE userId = :userId")
    fun getPermissionsForUser(userId: Int): Flow<List<UserPermission>>
}