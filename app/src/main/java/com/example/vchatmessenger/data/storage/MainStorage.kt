package com.example.vchatmessenger.data.storage

import android.content.Context
import com.example.vchatmessenger.data.models.retrofit.UserModel
import com.google.gson.Gson

private const val SharedPreferenceName = "main_preferences"
private const val User = "user"


class MainStorage(context: Context): Storage(context, SharedPreferenceName) {
    var user: UserModel?
        get() {
            val gson = Gson()
            val jsonUserModel = getString(User) ?: return null
            return gson.fromJson(jsonUserModel, UserModel::class.java)
        }

        set(userModel) {
            val gson = Gson()
            val jsonUserModel = gson.toJson(userModel)
            putString(User, jsonUserModel)
        }
}