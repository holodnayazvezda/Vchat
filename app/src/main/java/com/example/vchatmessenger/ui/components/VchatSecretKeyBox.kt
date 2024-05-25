package com.example.vchatmessenger.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatSecretKeyBox(number: Int, secretWord: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(1.dp, getSecondAppColor(), RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = number.toString(),
            color = getSecondAppColor(),
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 20.dp, end=20.dp, top = 15.dp, bottom = 15.dp)
        )
        Box(
            modifier = Modifier.fillMaxSize().padding(end = 15.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = secretWord,
                color = getSecondAppColor(),
                fontSize = 25.sp
            )
        }

    }
}