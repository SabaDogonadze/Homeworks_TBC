package com.example.tbchomework18.presentation.home

import com.example.tbchomework18.domain.get_user.Support
import com.example.tbchomework18.domain.get_user.UserModelDomain
import com.example.tbchomework18.domain.get_user.UsersDataResponse

/*fun UsersDataResponse.toPresenter():HomeUi{
    return HomeUi(
        page = page,
        perPage = perPage,
        total = total,
        totalPages = totalPages,
        data = data.map { it.toPresenter()},
        support = support.toPresenter()
    )
}*/

fun Support.toPresenter():SupportUI{
    return SupportUI(url = this.url, text = this.text)
}

fun UserModelDomain.toPresenter():UserModelUi{
    return UserModelUi(id = id, email = email, firstName = firstName, lastName = lastName, avatar = avatar)
}