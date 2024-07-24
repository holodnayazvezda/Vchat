package com.example.vchatmessenger.domain.usecase.profile

import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.data.models.retrofit.UserModel
import com.example.vchatmessenger.data.network.ImageLoader
import com.example.vchatmessenger.domain.usecase.chooseAvatar.ColorsWorker

class ProfileUsecase(
    private val imageLoader: ImageLoader
) {
    suspend fun getUserAvatar(user: UserModel): UserAvatar {
        val avatarBitmap = imageLoader.loadImageBitmap(user.avatar.avatar)
        val userAvatar = UserAvatar(
            avatar = avatarBitmap,
            currentBackgroundColor = ColorsWorker.getColorFromString(user.avatar.avatarBackgroundColor),
            avatarType = 2
        )
        return userAvatar
    }
}