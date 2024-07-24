package com.example.vchatmessenger.data.states

import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.data.models.retrofit.UserModel
import com.example.vchatmessenger.ui.theme.currentAppColor

data class ProfileState(
    var userAvatar: UserAvatar = UserAvatar(
        null,
        currentAppColor.mainAppColor
    ),
    var user: UserModel = UserModel(),
    val profileScreenElementsNamesToChange: List<String> = listOf(
        "пароль",
        "секретный ключ",
        "цвета приложения",
    )
)
