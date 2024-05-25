package com.example.vchatmessenger.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatOutlinedTextFieldWithHideButton(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
) {
    var visible by remember {
        mutableStateOf(false)
    }
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
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (visible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (visible) "Hide password" else "Show password"
            IconButton(onClick = {
                visible = !visible
            }) {
                Icon(
                    imageVector = image,
                    contentDescription = description,
                    modifier = Modifier.size(20.dp),
                    tint = getSecondAppColor()
                )
            }
        },
        visualTransformation =
        if (visible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}