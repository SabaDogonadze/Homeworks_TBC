package com.example.tbchomework18.data.remote.get_user

import com.example.tbchomework18.data.local.entity.UsersEntity
import com.example.tbchomework18.domain.get_user.UserModelDomain

fun UsersEntity.toDomainModel(): UserModelDomain {
    return UserModelDomain(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        avatar = this.avatar
    )
}
