package com.example.vchatmessenger.domain.usecase.chooseAvatar

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.vchatmessenger.data.models.UserAvatar


class AvatarGenerator(private var backgroundColor: Color, letter: String) {
    private var letter: String = letter.uppercase()

    fun generateAvatar(): UserAvatar {
        val bitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Настройка кисти для рисования фона
        val paint = Paint().apply {
            color = backgroundColor.toArgb()
            textAlign = Paint.Align.CENTER
            style = Paint.Style.FILL
        }

        // Рисование фона
        canvas.drawPaint(paint)
        // создание цвета текста (цветовые компоненты противоположны цветовым компонентам цвета фона)
        val textColor = ColorsWorker.getAvatarTextColor(backgroundColor)
        // Настройка кисти для рисования текста
        paint.apply {
            color = textColor.toArgb()
            textSize = 256f
        }
        // Определение координат для размещения текста в центре
        val x = bitmap.width / 2f
        val y = (bitmap.height / 2f) - ((paint.descent() + paint.ascent()) / 2f)

        // Рисование текста (первая буква имени пользователя) в центре изображения
        canvas.drawText(letter, x, y, paint)
        val userAvatar = UserAvatar(
            bitmap,
            backgroundColor
        )
        return userAvatar
    }
}
