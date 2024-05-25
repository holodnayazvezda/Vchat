package com.example.vchatmessenger.domain.usecase.signup

import com.example.vchatmessenger.data.network.VchatRepository

class SignUpUsecase(
    private val repository: VchatRepository?
) {
    fun checkName(name: String): String {
        if (name.isEmpty()) {
            return "Имя не указано"
        }
        return ""
    }

    suspend fun checkNickname(nickname: String): String {
        if (nickname.isEmpty()) {
            return "Никнейм не указан"
        } else {
            val result = repository?.checkNicknameForUser(nickname)
            if (result == 400) {
                return "Никнейм слишком короткий или содержит недопустимые символы"
            } else if (result == 500) {
                return "Этот никнейм уже занят"
            }
        }
        return ""
    }
}