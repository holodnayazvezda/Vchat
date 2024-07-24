package com.example.vchatmessenger.domain.usecase.chooseAvatar

import android.graphics.Bitmap
import com.example.vchatmessenger.data.models.retrofit.AvatarDTOModel
import com.example.vchatmessenger.data.models.retrofit.SignUpModel
import com.example.vchatmessenger.data.network.VchatRepository
import com.example.vchatmessenger.data.states.SignUpSharedViewModelState
import com.example.vchatmessenger.domain.usecase.avatar.AvatarUsecase

class ChooseAvatarUsecase(
    private val repository: VchatRepository?,
) {
    suspend fun uploadAvatar(avatar: Bitmap): String {
        val avatarPart = AvatarUsecase.createPartFromAvatarBitmap(avatar)
        val avatarFileNameOnServer = repository?.uploadAvatar(avatarPart)
        return avatarFileNameOnServer ?: ""
    }

    suspend fun createUser(signUpSharedViewModelState: SignUpSharedViewModelState): List<String> {
        val avatarBackgroundColor = ColorsWorker.convertColorToString(signUpSharedViewModelState.userAvatar.currentBackgroundColor)
        val signUpModel = SignUpModel(
            name = signUpSharedViewModelState.name,
            nickname = signUpSharedViewModelState.nickname,
            password = signUpSharedViewModelState.password,
            avatarDTO = AvatarDTOModel(
                avatar = signUpSharedViewModelState.userAvatarFileNameOnServer,
                avatarType = signUpSharedViewModelState.userAvatar.avatarType,
                avatarBackgroundColor = avatarBackgroundColor
            )
        )
        val secretKey = repository?.createUser(signUpModel)
        return secretKey ?: listOf()
    }
}