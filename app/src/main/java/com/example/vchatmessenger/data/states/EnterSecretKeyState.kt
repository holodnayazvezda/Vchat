package com.example.vchatmessenger.data.states

data class EnterSecretKeyState(
    val secretKeyWordsNumbers: List<Int> = listOf(0, 0, 0),
    val secretKey: List<String> = listOf("", "", ""),
    val errorSecretKey: String = "Секретный ключ не указан",
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false
)