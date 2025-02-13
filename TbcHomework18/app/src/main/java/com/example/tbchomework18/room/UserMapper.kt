package com.example.tbchomework18.room

fun UsersEntity.toRoomUser(): com.example.tbchomework18.room.UsersEntity {
    return com.example.tbchomework18.room.UsersEntity(
        id = this.id,
        avatar = this.avatar ?: "",
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )
}

fun com.example.tbchomework18.room.UsersEntity.toApiUser(): com.example.tbchomework18.data.remote.UserModel {
    return  com.example.tbchomework18.data.remote.UserModel(
        id = this.id,
        avatar = this.avatar,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}