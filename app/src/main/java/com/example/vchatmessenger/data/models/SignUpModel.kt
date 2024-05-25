package com.example.vchatmessenger.data.models

import com.google.gson.annotations.SerializedName

data class SignUpModel(
    @SerializedName("name") val name: String = "",
    @SerializedName("nickname") val nickname: String = "",
    @SerializedName("password") val password: String = "",
    @SerializedName("secretWords") val secretKey: List<String> = listOf(),
    @SerializedName("imageData") val image: String = "",
    @SerializedName("typeOfImage") val imageType: Int = 1
)