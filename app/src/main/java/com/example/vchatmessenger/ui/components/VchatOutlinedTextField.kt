package com.example.vchatmessenger.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = getSecondAppColor(),
            unfocusedTextColor = getSecondAppColor(),
            focusedBorderColor = getSecondAppColor(),
            unfocusedBorderColor = getSecondAppColor(),
            cursorColor = getSecondAppColor(),
            focusedContainerColor = getMainAppColor(),
            unfocusedContainerColor = getMainAppColor()
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 18.sp),
        shape = RoundedCornerShape(15.dp),
        label = {
            Text(
                text=placeholderText,
                color = getSecondAppColor()
            )
        }
    )
}