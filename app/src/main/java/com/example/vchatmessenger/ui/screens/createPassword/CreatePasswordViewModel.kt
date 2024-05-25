package com.example.vchatmessenger.ui.screens.createPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.vchatmessenger.domain.usecase.checkPassword.CreatePasswordUsecase

class CreatePasswordViewModel(
    private val createPasswordUsecase: CreatePasswordUsecase
) {
    var state by mutableStateOf(CreatePasswordState())
        private set

    fun updatePassword(password: String) {
        state = state.copy(
            password = password,
            errorPassword = createPasswordUsecase.checkEverything(password, state.confirmPassword)
        )
    }

    fun updateConfirmPassword(confirmPassword: String) {
        state = state.copy(
            confirmPassword = confirmPassword,
            errorPassword = createPasswordUsecase.checkEverything(state.password, confirmPassword)
        )
    }
}

data class CreatePasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val errorPassword: String = "Пароль не указан"
)