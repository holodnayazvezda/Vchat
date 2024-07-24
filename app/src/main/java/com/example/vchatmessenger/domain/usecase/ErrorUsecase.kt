package com.example.vchatmessenger.domain.usecase

import com.example.vchatmessenger.data.models.retrofit.UserModel
import java.util.Locale

class ErrorUsecase {
    companion object {
        fun getErrorText(
            errorMessage: String?,
            mainScreen: Boolean = false
        ): String {
            return if (errorMessage != null) {
                val errorMessageLowercase = errorMessage.lowercase(Locale.ROOT)
                if (
                    errorMessageLowercase.contains("unable to resolve") ||
                    errorMessageLowercase.contains("failed to connect")
                ) {
                    "Не удалось соедениться с сервером Вчат! Проверьте соединение с интернетом."
                } else if (errorMessageLowercase.contains("401")) {
                    if (mainScreen) {
                        "Авторизационные данные устарели! Попробуйте войти заново."
                    } else {
                        "Неверный авторизационные данные!"
                    }
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

        fun getErrorText(userModel: UserModel?): String {
            return if (userModel == null || userModel.name.isEmpty()) {
                "Авторизационные данные устарели! Попробуйте войти заново."
            } else {
                ""
            }
        }

        fun getLogOutAfterPressingOKInErrorDialog(errorMessage: String?): Boolean {
            return !errorMessage.isNullOrEmpty() && errorMessage.lowercase(Locale.ROOT).contains("401")
        }
    }
}