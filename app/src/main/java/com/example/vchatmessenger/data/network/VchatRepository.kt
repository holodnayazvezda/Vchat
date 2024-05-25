package com.example.vchatmessenger.data.network

import android.util.Base64
import com.example.vchatmessenger.data.models.SignUpModel
import com.example.vchatmessenger.data.models.UserModel

interface VchatRepository {
    suspend fun checkNicknameForUser(nickname: String): Int
    suspend fun createUser(signUpModel: SignUpModel): SignUpModel
    suspend fun checkIfUserExists(nickname: String): Boolean
    suspend fun authorizeUser(nickname: String, password: String): UserModel
}

class NetworkVchatRepository(
    private val vchatApiService: VchatApiService
): VchatRepository {
    override suspend fun checkNicknameForUser(nickname: String): Int {
        return vchatApiService.checkNicknameForUser(nickname)
    }

    override suspend fun createUser(signUpModel: SignUpModel): SignUpModel {
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
}