package com.example.vchatmessenger.domain.usecase.enterSecretKey

class EnterSecretKeyUsecase {
    fun checkSecretKey(secretKey: List<String>): String {
        return if (secretKey[0].isEmpty() || secretKey[1].isEmpty() || secretKey[2].isEmpty()) {
            "Секретный ключ не указан"
        } else {
            ""
        }
    }
}