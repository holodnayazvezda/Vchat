package com.example.vchatmessenger.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.vchatmessenger.data.models.ApplicationColors

val appColors = listOf(
    ApplicationColors(mainAppColor = Color(0xFF0E0B0B), secondAppColor = Color(0xFFBEBEBE)),
    ApplicationColors(mainAppColor = Color(0xFF344129), secondAppColor = Color(0xFFACB78E)),
    ApplicationColors(mainAppColor = Color(0xFFB00000), secondAppColor = Color(0xFFACB78E)),
    ApplicationColors(mainAppColor = Color(0xFFE06B65), secondAppColor = Color(0xFFFFFFFF)),
    ApplicationColors(mainAppColor = Color(0xFF680F38), secondAppColor = Color(0xFFFCC480)),
    ApplicationColors(mainAppColor = Color(0xFF482826), secondAppColor = Color(0xFFE9B08F)),
    ApplicationColors(mainAppColor = Color(0xFF4B3C26), secondAppColor = Color(0xFFF8EC7B)),
    ApplicationColors(mainAppColor = Color(0xFF3F888F), secondAppColor = Color(0xFFF8EC7B)),
    ApplicationColors(mainAppColor = Color(0xFF252850), secondAppColor = Color(0xFF9DB1CC)),
    ApplicationColors(mainAppColor = Color(0xFFFAFFDF), secondAppColor = Color(0xFFFF0090)),
    ApplicationColors(mainAppColor = Color(0xFF1B4141), secondAppColor = Color(0xFFE4D0E3)),
    ApplicationColors(mainAppColor = Color(0xFF14E6C3), secondAppColor = Color(0xFFFAFFDF)),
    ApplicationColors(mainAppColor = Color(0xFF9966CC), secondAppColor = Color(0xFFFFE1EC)),
    ApplicationColors(mainAppColor = Color(0xFF644483), secondAppColor = Color(0xFFF3D678)),
    ApplicationColors(mainAppColor = Color(0xFFE63641), secondAppColor = Color(0xFFFAFFDF))
)

val currentAppColor = appColors[1]

@Composable
fun getMainAppColor() = if (isSystemInDarkTheme()) currentAppColor.mainAppColor else currentAppColor.secondAppColor

@Composable
fun getSecondAppColor() = if (isSystemInDarkTheme()) currentAppColor.secondAppColor else currentAppColor.mainAppColor

fun getUserAvatarDefaultBackgroundColor() = currentAppColor.mainAppColor