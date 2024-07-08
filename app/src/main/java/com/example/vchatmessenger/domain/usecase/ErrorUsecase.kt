package com.example.vchatmessenger.domain.usecase

import java.util.Locale

class ErrorUsecase {
    companion object {
        fun getErrorText(errorMessage: String?): String {
            return if (errorMessage != null) {
                val errorMessageLowercase = errorMessage.lowercase(Locale.ROOT)
                if (
                    errorMessageLowercase.contains("unable to resolve") ||
                    errorMessageLowercase.contains("failed to connect")
                ) {
                    "Не удалось соедениться с сервером Вчат! Проверьте соединение с интернетом."
                } else if (errorMessageLowercase.contains("401")) {
                    "Неверный авторизационные данные!"
                } else {
                    errorMessageLowercase.substringBefore('.')
                }
            } else {
                "Неизвестная ошибка"
            }
        }

        fun <T> getErrorText(obj: T): String {
            return if (obj.toString().isEmpty()) {
                "Неизвестная ошибка"
            } else {
                ""
            }
        }
    }
}