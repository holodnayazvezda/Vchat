package com.example.vchatmessenger.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle
            )
        },
        text = {
            Text(
                text = dialogText
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    text = "ОК",
                    color = getSecondAppColor(),
                    fontSize = 15.sp
                )
            }
        },
        containerColor = getMainAppColor(),
        textContentColor = getSecondAppColor(),
        titleContentColor = getSecondAppColor(),
        iconContentColor = getSecondAppColor(),
    )
}