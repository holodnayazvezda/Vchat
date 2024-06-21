package com.example.vchatmessenger.domain.usecase.chooseAvatar

import androidx.compose.ui.graphics.Color
import com.example.vchatmessenger.ui.theme.appColors
import com.example.vchatmessenger.ui.theme.currentAppColor


class ColorsWorker {
    fun getRandomBackgroundColor(currentBackgroundColor: Color): Color {
        val colors = appColors.filter {
            it.mainAppColor != currentBackgroundColor
                    && it != currentAppColor
        }
        return colors.random().mainAppColor
    }

    fun getTextColor(backgroundColor: Color): Color {
        val backgroundColorRGBSum = backgroundColor.red + backgroundColor.green + backgroundColor.blue

        return if (backgroundColorRGBSum > 2) {
            Color.Black
        } else {
            Color.White
        }
    }
}
