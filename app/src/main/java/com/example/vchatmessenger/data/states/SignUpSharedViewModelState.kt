package com.example.vchatmessenger.data.states

import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.ui.theme.currentAppColor

data class SignUpSharedViewModelState(
    val name: String = "",
    val nickname: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val newPassword: String = "",
    val newConfirmPassword: String = "",
    val secretKey: List<String> = listOf(),
    val userAvatar: UserAvatar = UserAvatar(
        null,
        currentAppColor.mainAppColor
    ),
    val userAvatarFileNameOnServer: String = ""
)
