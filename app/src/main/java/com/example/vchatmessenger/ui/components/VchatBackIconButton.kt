package com.example.vchatmessenger.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.vchatmessenger.R
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatBackIconButton(route: String, navController: NavHostController) {
    IconButton(onClick = {
        navController.navigate(route)
    }) {
        Icon(
            painter = painterResource(id = R.drawable.back_arrow),
            contentDescription = null,
            tint = getSecondAppColor()
        )
    }
}