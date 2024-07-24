package com.example.vchatmessenger.data.storage

import android.content.Context

open class Storage(context: Context, sharedPreferenceName: String) {
    private val prefs = context.getSharedPreferences(
        sharedPreferenceName,
        Context.MODE_PRIVATE
    )

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    fun putString(key: String, value: String?) {
        prefs.edit()
            .putString(key, value)
            .apply()
    }

    fun deleteString(key: String) {
        prefs.edit().remove(key).apply()
    }
}