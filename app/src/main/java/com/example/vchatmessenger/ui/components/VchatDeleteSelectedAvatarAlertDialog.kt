package com.example.vchatmessenger.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatDeleteSelectedAvatarAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(
                text = "Удалить загруженный аватар?"
            )
        },
        text = {
            Text(
                text = "После удаления загруженного аватара новый будет сгенерирован автоматически"
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) {
                Text(
                    text = "ОК",
                    color = getSecondAppColor(),
                    fontSize = 15.sp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(
                    text = "Отмена",
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