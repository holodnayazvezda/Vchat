package com.example.vchatmessenger.domain.usecase.enterSecretKey

import com.example.vchatmessenger.data.network.VchatRepository
import kotlin.random.Random

class EnterSecretKeyUsecase(
    private val repository: VchatRepository?
) {
    suspend fun checkSecretKey(
        nickname: String,
        secretKeyWordsNumbers: List<Int>,
        secretKey: List<String>
    ): String {
        if (secretKey[0].isEmpty() || secretKey[1].isEmpty() || secretKey[2].isEmpty()) {
            return "Секретный ключ не указан"
        } else {
            val result = repository?.checkSecretKey(nickname, secretKeyWordsNumbers, secretKey)
            if (result == null || !result) {
                return "Введен неверный секретный ключ"
            }
            return ""
        }
    }

    fun generateSecretKeyWordsNumbers(): List<Int> {
        val randomSecretKeyWordsNumbers = mutableListOf<Int>()

        while (randomSecretKeyWordsNumbers.size < 3) {
            val randomNumber = Random.nextInt(0, 5)
            if (!randomSecretKeyWordsNumbers.contains(randomNumber)) {
                randomSecretKeyWordsNumbers.add(randomNumber)
            }
        }

        return randomSecretKeyWordsNumbers.toList().sorted()
    }
}