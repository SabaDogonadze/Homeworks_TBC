package com.example.tbcclasswork5.room
import com.example.tbcclasswork5.model.User

fun User.toRoomUser(): com.example.tbcclasswork5.room.User {
    return  com.example.tbcclasswork5.room.User(
        id = this.id,
        avatar = this.avatar?:"",
        firstName = this.firstName,
        lastName = this.lastName,
        about = this.about,
        activationStatus = this.activationStatus
    )
}

fun com.example.tbcclasswork5.room.User.toApiUser(): com.example.tbcclasswork5.model.User {
    return com.example.tbcclasswork5.model.User(
        id = this.id,
        avatar = this.avatar,
        firstName = this.firstName,
        lastName = this.lastName,
        about = this.about,
        activationStatus = this.activationStatus
    )
}
