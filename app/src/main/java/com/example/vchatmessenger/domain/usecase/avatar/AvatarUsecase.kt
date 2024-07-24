package com.example.vchatmessenger.domain.usecase.avatar

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.domain.usecase.chooseAvatar.AvatarGenerator
import com.example.vchatmessenger.domain.usecase.chooseAvatar.ColorsWorker
import com.example.vchatmessenger.ui.theme.currentAppColor
import com.vdurmont.emoji.EmojiParser
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream


class AvatarUsecase {
    companion object {

        fun generateAvatar(
            name: String,
            currentBackgroundColor: Color = currentAppColor.mainAppColor,
            requiredBackgroundColor: Color? = null
        ): UserAvatar {
            var firstLetter = name[0].toString()
            val emojis = EmojiParser.extractEmojis(name)
            if (emojis.size != 0 && name.startsWith(emojis[0])) {
                firstLetter = emojis[0]
            }
            val randomColor = ColorsWorker.getRandomBackgroundColor(currentBackgroundColor)
            val avatarGenerator = AvatarGenerator(
                randomColor,
                firstLetter
            )
            val generatedAvatar = avatarGenerator.generateAvatar()
            return generatedAvatar
        }

        fun createPartFromAvatarBitmap(bitmap: Bitmap): MultipartBody.Part {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            val requestBody =
                byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull(), 0, byteArray.size)
            return MultipartBody.Part.createFormData("file", "file.jpg", requestBody)
        }

    }
}