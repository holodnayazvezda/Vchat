package com.example.vchatmessenger.data.models

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color

data class UserAvatar(
    val avatar: Bitmap?,
    val currentBackgroundColor: Color,
    /* 1 - аватар был сгенерирован автоматически; 2 - аватар был загружен пользователем */
    val avatarType: Int = 1
)
