package com.example.vchatmessenger.data.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.vchatmessenger.data.storage.AuthStorage


class ImageLoader(
    private val context: Context,
    private val authStorage: AuthStorage
) {
    private val baseUrl = "https://v-chat.ru/files/view?filename="

    private fun getAuthorizationCredentials(): String {
        val credentials = "${authStorage.nickname}:${authStorage.password}"
        val authorizationCredentials = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        return authorizationCredentials
    }

    suspend fun loadImageBitmap(filename: String): Bitmap {
        val url = "$baseUrl$filename"
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .addHeader("accept", "*/*")
            .addHeader("Authorization", getAuthorizationCredentials())
            .build()
        val imageDrawable = imageLoader.execute(request).drawable
        val imageBitmap = (imageDrawable as BitmapDrawable).bitmap
        return imageBitmap
    }
}