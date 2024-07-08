package com.example.vchatmessenger.data.network

import android.util.Base64
import com.example.vchatmessenger.data.models.retrofit.SignUpModel
import com.example.vchatmessenger.data.models.retrofit.UserModel
import okhttp3.MultipartBody

interface VchatRepository {
    suspend fun checkNicknameForUser(nickname: String): Int
    suspend fun createUser(signUpModel: SignUpModel): List<String>
    suspend fun checkIfUserExists(nickname: String): Boolean
    suspend fun authorizeUser(nickname: String, password: String): UserModel
    suspend fun checkSecretKey(
        nickname: String,
        secretKeyWordsNumbers: List<Int>,
        secretKey: List<String>
    ): Boolean
    suspend fun changePasswordUsingSecretKey(
        nickname: String,
        secretKeyWordsNumbers: List<Int>,
        secretKey: List<String>,
        password: String
    )
    suspend fun uploadAvatar(avatar: MultipartBody.Part): String
}

class NetworkVchatRepository(
    private val vchatApiService: VchatApiService
): VchatRepository {
    override suspend fun checkNicknameForUser(nickname: String): Int {
        return vchatApiService.checkNicknameForUser(nickname)
    }

    override suspend fun createUser(signUpModel: SignUpModel): List<String> {
        return vchatApiService.createUser(signUpModel)
    }

    override suspend fun checkIfUserExists(nickname: String): Boolean {
        return vchatApiService.checkIfUserExists(nickname)
    }

    override suspend fun authorizeUser(nickname: String, password: String): UserModel {
        val credentials = "$nickname:$password"
        val authorizationCredentials = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        return vchatApiService.authorizeUser(authorizationCredentials)
    }

    override suspend fun checkSecretKey(
        nickname: String,
        secretKeyWordsNumbers: List<Int>,
        secretKey: List<String>
    ): Boolean {
        return vchatApiService.checkSecretKey(
            nickname,
            secretKeyWordsNumbers,
            secretKey
        )
    }

    override suspend fun changePasswordUsingSecretKey(
        nickname: String,
        secretKeyWordsNumbers: List<Int>,
        secretKey: List<String>,
        password: String
    ) {
        vchatApiService.changePasswordUsingSecretKey(
            nickname,
            secretKeyWordsNumbers,
            secretKey,
            password
        )
    }

    override suspend fun uploadAvatar(avatar: MultipartBody.Part): String {
        return vchatApiService.uploadAvatar(avatar)
    }
}