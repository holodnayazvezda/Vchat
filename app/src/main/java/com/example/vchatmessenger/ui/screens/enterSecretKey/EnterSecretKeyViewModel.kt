package com.example.vchatmessenger.ui.screens.enterSecretKey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.vchatmessenger.domain.usecase.enterSecretKey.EnterSecretKeyUsecase
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel

class EnterSecretKeyViewModel(
    private val sharedVM: LogInSharedViewModel,
    private val enterSecretKeyUsecase: EnterSecretKeyUsecase
) {
    var state by mutableStateOf(EnterSecretKeyState())
        private set

    fun updateSecretKey(secretKeyNumber: Int, secretKey: String) {
        val secretKeys = state.secretKey.toMutableList()
        secretKeys[secretKeyNumber] = secretKey
        state = state.copy(
            secretKey = secretKeys,
            errorSecretKey = enterSecretKeyUsecase.checkSecretKey(secretKeys)
        )
    }
}

data class EnterSecretKeyState(
    val secretKeyNumbers: List<Int> = listOf(0, 0, 0),
    val secretKey: List<String> = listOf("", "", ""),
    val errorSecretKey: String = "Секретный ключ не указан"
)