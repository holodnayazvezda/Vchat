package com.example.vchatmessenger.data.states

data class LogInState(
    val nickname: String = "",
    val errorNickname: String = "Никнейм не указан",
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false
)
