package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.data.states.SignUpSharedViewModelState

class SignUpSharedViewModel : ViewModel() {
    private var _data by mutableStateOf(SignUpSharedViewModelState())
    val data
        get() = _data

    fun changeName(name: String) {
        _data = _data.copy(name = name)
    }

    fun changeNickname(nickname: String, ) {
        _data = _data.copy(nickname = nickname)
    }

    fun changePassword(password: String, onUpdate: Boolean = false) {
        _data = if (onUpdate) {
            _data.copy(newPassword = password)
        } else {
            _data.copy(password = password)
        }
    }

    fun changeConfirmPassword(confirmPassword: String, onUpdate: Boolean = false) {
        _data = if (onUpdate) {
            _data.copy(newConfirmPassword = confirmPassword)
        } else {
            _data.copy(confirmPassword = confirmPassword)
        }
    }

    fun changeSecretKey(secretKey: List<String>) {
        _data = _data.copy(secretKey = secretKey)
    }

    fun changeUserAvatar(userAvatar: UserAvatar) {
        _data = _data.copy(userAvatar = userAvatar)
    }

    fun changeUserAvatarFileNameOnServer(userAvatarFileNameOnServer: String) {
        _data = _data.copy(userAvatarFileNameOnServer = userAvatarFileNameOnServer)
    }
}

