package com.example.vchatmessenger.data.states

import com.example.vchatmessenger.data.models.retrofit.UserModel

data class MainScreenState(
    var user: UserModel = UserModel(),
    val errorCredentials: String = "Авторизационные данные устарели! Попробуйте войти заново.",
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false,
    val logOutAfterConfirmingInErrorDialog: Boolean = false
)
