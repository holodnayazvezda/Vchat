package com.example.vchatmessenger.data.network

import com.example.vchatmessenger.data.models.retrofit.SignUpModel
import com.example.vchatmessenger.data.models.retrofit.UserModel
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface VchatApiService {
    @PUT("user/change_password_by_secret_key")
    suspend fun changePasswordUsingSecretKey(
        @Query("userNickname") nickname: String,
        @Query("secretKeyWordsNumbers") secretKeyWordsNumbers: List<Int>,
        @Query("secretKeyWords") secretKey: List<String>,
        @Query("newPassword") password: String
    )

    @POST("user/create")
    suspend fun createUser(
        @Body signUpModel: SignUpModel
    ): List<String>

    @Multipart
    @POST("files/upload_avatar")
    suspend fun uploadAvatar(@Part file: MultipartBody.Part): String

    @GET("nickname/checkForUser")
    suspend fun checkNicknameForUser(
        @Query("username") nickname: String
    ): Int

    @GET("user/exists")
    suspend fun checkIfUserExists(
        @Query("userNickname") nickname: String
    ): Boolean

    @GET("user/get")
    suspend fun authorizeUser(
        @Header("Authorization") authorizationCredentials: String
    ): UserModel

    @GET("user/check_secret_key")
    suspend fun checkSecretKey(
        @Query("userNickname") nickname: String,
        @Query("secretKeyWordsNumbers") secretKeyWordsNumbers: List<Int>,
        @Query("secretKeyWords") secretKey: List<String>
    ): Boolean
}