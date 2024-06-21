package com.example.vchatmessenger.data.states

data class CreatePasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val errorPassword: String = "Пароль не указан",
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false
)
