package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LogInSharedViewModel : ViewModel() {
    private var _data by mutableStateOf(LogInData())
    val data
        get() = _data

    fun changeData(nickname: String = "") {
        if (nickname.isNotEmpty()) {
            _data = _data.copy(nickname = nickname)
        }
    }
}

data class LogInData(
    val nickname: String = ""
)