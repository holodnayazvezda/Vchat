package com.example.vchatmessenger.domain.usecase.chooseAvatar

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.vchatmessenger.ui.theme.appColors
import com.example.vchatmessenger.ui.theme.currentAppColor


class ColorsWorker {
    companion object {

        fun getRandomBackgroundColor(currentBackgroundColor: Color): Color {
            val colors = appColors.filter {
                it.mainAppColor != currentBackgroundColor
                        && it != currentAppColor
            }
            return colors.random().mainAppColor
        }

        fun getAvatarTextColor(backgroundColor: Color): Color {
            val backgroundColorRGBSum = backgroundColor.red + backgroundColor.green + backgroundColor.blue

            return if (backgroundColorRGBSum > 2) {
                Color.Black
            } else {
                Color.White
            }
        }

        fun convertColorToString(color: Color): String {
            return String.format("0xFF%06X", (0xFFFFFF and color.toArgb()))
        }

        /* Converts a string containing a color in the format "0xFFFFFFFF" into an object of type androidx.compose.ui.graphics.Color */
        fun getColorFromString(colorString: String): Color {
            val encodedValue = colorString.substring(2).toLong(16)
            return Color(encodedValue.toInt())
        }

    }
}
