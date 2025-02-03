package com.example.tbcclasswork5.room

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val avatar: String,
    val firstName: String,
    val lastName: String,
    val about: String?,
    val activationStatus: Double
)

@Entity(tableName = "permissions")
data class Permission(
    @PrimaryKey val name: String
)

@Entity(primaryKeys = ["userId", "permissionName"])
data class UserPermission(
    val userId: Int,
    val permissionName: String
)
