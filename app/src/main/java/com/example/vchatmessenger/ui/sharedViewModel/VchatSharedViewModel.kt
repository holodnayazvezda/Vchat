package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.vchatmessenger.data.Storage

class VchatSharedViewModel(
    private val storage: Storage
): ViewModel() {
    var displayCreatePasswordScreenAsCreateNewPasswordScreen by mutableStateOf(false)

    fun writeAuthDataToSharedPreferences(
        nickname: String,
        password: String
    ) {
        storage.isStartup = "true"
        storage.nickname = nickname
        storage.password = password
    }
}