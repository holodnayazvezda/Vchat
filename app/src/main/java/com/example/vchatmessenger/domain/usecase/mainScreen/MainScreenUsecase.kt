package com.example.vchatmessenger.domain.usecase.mainScreen

import com.example.vchatmessenger.data.models.retrofit.UserModel
import com.example.vchatmessenger.data.network.VchatRepository

class MainScreenUsecase(
    private val repository: VchatRepository?
) {
    suspend fun authorizeUser(nickname: String, password: String): UserModel? {
        return repository?.authorizeUser(nickname, password)
    }
}