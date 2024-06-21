package com.example.vchatmessenger.data.states

data class EnterPasswordState(
    var password: String = "",
    val errorPassword: String = "Пароль не указан",
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false
)
