package com.example.vchatmessenger.data.models.retrofit

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("nickname") val nickname: String = "",
    @SerializedName("avatar") val avatar: AvatarDTOModel = AvatarDTOModel()
)
