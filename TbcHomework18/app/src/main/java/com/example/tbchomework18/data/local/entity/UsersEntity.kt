package com.example.tbchomework18.data.local.entity

import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity(tableName = "user_table")
data class UsersEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val email:String,
    val firstName:String,
    val lastName:String,
    val avatar:String
)
