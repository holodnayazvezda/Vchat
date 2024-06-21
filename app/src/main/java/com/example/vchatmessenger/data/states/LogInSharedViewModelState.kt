package com.example.vchatmessenger.data.states

data class LogInSharedViewModelState(
    val nickname: String = "",
    val password: String = "",
    val secretKeyWordsNumbers: List<Int> = listOf(0, 0, 0),
    val secretKey: List<String> = listOf("", "", "")
)
