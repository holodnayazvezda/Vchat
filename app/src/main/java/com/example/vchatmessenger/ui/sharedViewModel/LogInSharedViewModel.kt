package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vchatmessenger.data.states.LogInSharedViewModelState

class LogInSharedViewModel : ViewModel() {
    private var _data by mutableStateOf(LogInSharedViewModelState())
    val data
        get() = _data

    fun changeNickname(nickname: String) {
        _data = _data.copy(nickname = nickname)
    }

    fun changePassword(password: String) {
        _data = _data.copy(password = password)
    }

    fun changeSecretKeyWordsNumbers(secretKeyWordsNumbers: List<Int>) {
        _data = _data.copy(secretKeyWordsNumbers = secretKeyWordsNumbers)
    }

    fun updateSecretKey(
        secretKeyWordNumber: Int,
        secretKeyWord: String
    ) {
        if (secretKeyWordNumber in 0..2) {
            val newSecretKey = _data.secretKey.toMutableList()
            newSecretKey[secretKeyWordNumber] = secretKeyWord
            _data = _data.copy(secretKey = newSecretKey)
        }
    }
}