package com.example.vchatmessenger.domain.usecase.checkPassword


class CreatePasswordUsecase {
    private fun checkCorrectness(password: String): String {
        return if (password.isEmpty()) {
            "Пароль не указан"
        } else if (password.length < 8) {
            "Пароль должен содержать минимум 8 символов"
        } else if (password.length > 30) {
            "Длина пароля не должна превышать 30 символов"
        } else if (!password.matches(".*\\d.*".toRegex())) {
            "Пароль должен содержать хотя бы одну цифру"
        } else if (!password.matches(".*[a-z].*".toRegex())) {
            "Пароль должен содержать хотя бы одну строчную букву"
        } else if (!password.matches(".*[A-Z].*".toRegex())) {
            "Пароль должен содержать хотя бы одну заглавную букву"
        } else if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*".toRegex())) {
            "Пароль должен содержать хотя бы 1 специальный символ"
        } else if (!password.matches("[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]*".toRegex())) {
            "Пароль может сожержать только строчные и заглавные букв A-Z, цифры и специальные символы"
        } else {
            ""
        }
    }

    private fun checkConfirmation(password: String, passwordConfirmation: String): String {
        return if (password != passwordConfirmation) {
            "Пароли должны совпадать"
        } else {
            ""
        }
    }

    fun checkEverything(password1: String, password2: String): String {
        val res = checkCorrectness(password1)
        return res.ifEmpty {
            return checkConfirmation(password1, password2)
        }
    }
}

