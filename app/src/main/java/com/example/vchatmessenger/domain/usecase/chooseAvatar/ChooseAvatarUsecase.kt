package com.example.vchatmessenger.domain.usecase.chooseAvatar

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.data.models.retrofit.AvatarDTOModel
import com.example.vchatmessenger.data.models.retrofit.SignUpModel
import com.example.vchatmessenger.data.network.VchatRepository
import com.example.vchatmessenger.data.states.SignUpSharedViewModelState
import com.example.vchatmessenger.domain.usecase.avatar.AvatarUsecase
import com.vdurmont.emoji.EmojiParser

class ChooseAvatarUsecase(
    private val repository: VchatRepository?,
) {
    fun generateAvatar(
        name: String,
        currentBackgroundColor: Color,
    ): UserAvatar {
        var firstLetter = name[0].toString()
        val emojis = EmojiParser.extractEmojis(name)
        if (emojis.size != 0 && name.startsWith(emojis[0])) {
            firstLetter = emojis[0]
        }
        val randomColor = ColorsWorker().getRandomBackgroundColor(currentBackgroundColor)
        val avatarGenerator = AvatarGenerator(
            randomColor,
            firstLetter
        )
        val generatedAvatar = avatarGenerator.generateAvatar()
        return generatedAvatar
    }

    suspend fun uploadAvatar(avatar: Bitmap): String {
        val avatarUsecase = AvatarUsecase()
        val avatarPart = avatarUsecase.createPartFromAvatarBitmap(avatar)
        val avatarFileNameOnServer = repository?.uploadAvatar(avatarPart)
        return avatarFileNameOnServer ?: ""
    }

    suspend fun createUser(signUpSharedViewModelState: SignUpSharedViewModelState): List<String> {
        val avatarBackgroundColor = String.format("0xFF%06X", (0xFFFFFF and signUpSharedViewModelState.userAvatar.currentBackgroundColor.toArgb()))
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