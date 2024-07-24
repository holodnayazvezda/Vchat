package com.example.vchatmessenger.data.storage

import android.content.Context

private const val SharedPreferenceName = "auth_preferences"
private const val IsStartup = "is_startup"
private const val Nickname = "username"
private const val Password = "password"


class AuthStorage(context: Context): Storage(context, SharedPreferenceName) {
    var nickname: String?
        get() {
            val storedUsername = getString(Nickname)
            return storedUsername
        }
        set(value) {
            putString(Nickname, value)
        }

    var password: String?
        get() {
            val storedPassword = getString(Password)
            return storedPassword
        }
        set(value) {
            putString(Password, value)
        }

    var isStartup: String?
        get() {
            return getString(IsStartup)
        }
        set(value) {
            putString(IsStartup, value)
        }

    fun clearAuthData() {
        deleteString(IsStartup)
        deleteString(Nickname)
        deleteString(Password)
    }
}