package com.example.vchatmessenger.domain.usecase.enterPassword

import com.example.vchatmessenger.data.network.VchatRepository

class EnterPasswordUsecase(
    private val repository: VchatRepository?
) {
    suspend fun checkPassword(nickname: String, password: String): String {
        if (password.isEmpty()) {
            return "Пароль не указан"
        } else {
            val result = repository?.authorizeUser(nickname, password)
            if (result == null || result.name.isEmpty()) {
                return "Неверный пароль!"
            }
        }
        return ""
    }
}