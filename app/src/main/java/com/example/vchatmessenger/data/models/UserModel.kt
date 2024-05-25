package com.example.vchatmessenger.data.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("nickname") val nickname: String = "",
    @SerializedName("imageData") val imageData: String = "",
    @SerializedName("typeOfImage") val typeOfImage: Int = 0
)
