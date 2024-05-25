package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vchatmessenger.data.models.SignUpModel

class SignUpSharedViewModel : ViewModel() {
    private var _data by mutableStateOf(SignUpModel())
    val data
        get() = _data

    fun changeData(
        name: String = "",
        nickname: String = "",
        password: String = "",
        secretKey: List<String> = listOf()
    ) {
        if (name.isNotEmpty()) {
            _data = _data.copy(
                name = name
            )
        }
        if (nickname.isNotEmpty()) {
            _data = _data.copy(
                nickname = nickname
            )
        }
        if (password.isNotEmpty()) {
            _data = _data.copy(
                password = password
            )
        }
        if (secretKey.isNotEmpty()) {
            _data = _data.copy(
                secretKey = secretKey
            )
        }
    }
}

