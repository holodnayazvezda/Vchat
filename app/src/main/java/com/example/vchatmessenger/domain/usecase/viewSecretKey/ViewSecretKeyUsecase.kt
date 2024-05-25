package com.example.vchatmessenger.domain.usecase.viewSecretKey

import com.example.vchatmessenger.data.models.SignUpModel
import com.example.vchatmessenger.data.network.VchatRepository

class ViewSecretKeyUsecase(
    private val repository: VchatRepository?
) {
    suspend fun createUser(signUpModel: SignUpModel): Boolean {
        val registeredUser = repository?.createUser(signUpModel)
        return registeredUser != null
    }
}