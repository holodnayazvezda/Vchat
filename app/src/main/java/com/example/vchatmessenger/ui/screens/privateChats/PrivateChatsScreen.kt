package com.example.vchatmessenger.ui.screens.privateChats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.vchatmessenger.ui.theme.getMainAppColor

@Composable
fun PrivateChatsScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getMainAppColor()),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "PrivateChats",
            color = Color.White
        )
    }
}