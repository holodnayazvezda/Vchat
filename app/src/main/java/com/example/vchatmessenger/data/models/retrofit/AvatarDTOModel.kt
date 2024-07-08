package com.example.vchatmessenger.data.models.retrofit

import com.google.gson.annotations.SerializedName

data class AvatarDTOModel(
    @SerializedName("avatarFileName") val avatar: String = "",
    @SerializedName("avatarType") val avatarType: Int = 0,
    @SerializedName("avatarBackgroundColor") val avatarBackgroundColor: String = ""
)
