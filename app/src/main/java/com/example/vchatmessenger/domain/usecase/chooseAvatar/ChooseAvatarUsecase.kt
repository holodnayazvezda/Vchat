package com.example.vchatmessenger.domain.usecase.chooseAvatar

import androidx.compose.ui.graphics.Color
import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.data.models.retrofit.SignUpModel
import com.example.vchatmessenger.data.network.VchatRepository
import com.example.vchatmessenger.data.states.SignUpSharedViewModelState
import com.vdurmont.emoji.EmojiParser

class ChooseAvatarUsecase(
    private val repository: VchatRepository?
) {
    fun generateAvatar(
        name: String,
        currentBackgroundColor: Color
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

    suspend fun createUser(signUpSharedViewModelState: SignUpSharedViewModelState): List<String> {

        val signUpModel = SignUpModel(
            name = signUpSharedViewModelState.name,
            nickname = signUpSharedViewModelState.nickname,
            password = signUpSharedViewModelState.password,
            avatarData = "string",
            avatarType = signUpSharedViewModelState.userAvatar.avatarType
        )
        val secretKey = repository?.createUser(signUpModel)
        return secretKey ?: listOf()
    }
}