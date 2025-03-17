package com.example.tbchomework18.data.local.mapper

import com.example.tbchomework18.data.local.entity.UsersEntity
import com.example.tbchomework18.data.remote.get_user.UserModel

fun UsersEntity.toRoomUser(): UsersEntity {
    return UsersEntity(
        id = this.id,
        avatar = this.avatar ?: "",
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )
}

fun UsersEntity.toApiUser(): UserModel {
    return  UserModel(
        id = this.id,
        avatar = this.avatar,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}