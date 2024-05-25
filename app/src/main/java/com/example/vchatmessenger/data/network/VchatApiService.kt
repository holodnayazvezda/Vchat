package com.example.vchatmessenger.data.network

import com.example.vchatmessenger.data.models.SignUpModel
import com.example.vchatmessenger.data.models.UserModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface VchatApiService {
    @GET("nickname/checkForUser")
    suspend fun checkNicknameForUser(
        @Query("username") nickname: String
    ): Int

    @POST("user/create")
    suspend fun createUser(
        @Body signUpModel: SignUpModel
    ): SignUpModel

    @GET("user/exists")
    suspend fun checkIfUserExists(
        @Query("userNickname") nickname: String
    ): Boolean

    @GET("user/get")
    suspend fun authorizeUser(
        @Header("Authorization") authorizationCredentials: String
    ): UserModel
}