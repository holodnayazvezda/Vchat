package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.storage.AuthStorage
import com.example.vchatmessenger.domain.navigation.ScreensRouts

class VchatSharedViewModel(
    private val authStorage: AuthStorage
): ViewModel() {
    var displayCreatePasswordScreenAsCreateNewPasswordScreen by mutableStateOf(false)

    fun writeAuthDataToSharedPreferences(
        nickname: String,
        password: String
    ) {
        authStorage.isStartup = "true"
        authStorage.nickname = nickname
        authStorage.password = password
    }

    fun logOut(navController: NavHostController) {
        authStorage.clearAuthData()
        navController.navigate(ScreensRouts.Welcome.route)
    }
}