package com.example.vchatmessenger.data

import android.content.Context

private const val SharedPreferenceName = "vchat_preferences"
private const val IsStartup = "is_startup"
private const val Nickname = "username"
private const val Password = "password"

class Storage(context: Context) {
    private val prefs = context.getSharedPreferences(
        SharedPreferenceName,
        Context.MODE_PRIVATE
    )

    private fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    private fun putString(key: String, value: String?) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }

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
}