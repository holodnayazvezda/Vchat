package com.example.vchatmessenger.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatNextFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier.padding(bottom = 15.dp),
        onClick = onClick,
        shape = CircleShape,
        containerColor = getSecondAppColor()
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Стрелка",
            tint = getMainAppColor(),
            modifier = Modifier.size(30.dp)
        )
    }
}