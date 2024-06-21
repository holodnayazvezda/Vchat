package com.example.vchatmessenger.data.states

data class SignUpState(
    val name: String = "",
    val errorName: String = "Имя не указано",
    val nickname: String = "",
    val errorNickname: String = "Никнейм не указан",
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false
)
