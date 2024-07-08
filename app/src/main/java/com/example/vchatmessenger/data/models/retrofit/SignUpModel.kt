package com.example.vchatmessenger.data.models.retrofit

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("name") val name: String = "",
    @SerializedName("nickname") val nickname: String = "",
    @SerializedName("password") val password: String = "",
    @SerializedName("avatarDTO") val avatarDTO: AvatarDTOModel = AvatarDTOModel(
        "",
        0,
        ""
    )
)