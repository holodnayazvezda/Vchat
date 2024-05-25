package com.example.vchatmessenger.domain.usecase.login

import com.example.vchatmessenger.data.network.VchatRepository

class LogInUsecase(
    private val repository: VchatRepository?
) {
    suspend fun checkNickname(nickname: String): String {
        if (nickname.isEmpty()) {
            return "Никнейм не указан"
        } else {
            val result = repository?.checkIfUserExists(nickname)
            if (!result!!) {
                return "Пользователь с никнеймом @$nickname не найден!"
            }
        }
        return ""
    }
}