package com.example.vchatmessenger.data.states

data class ChooseAvatarState(
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false,
    val error: String = ""
)