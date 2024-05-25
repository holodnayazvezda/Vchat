package com.example.vchatmessenger.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun VchatInfoText(
    questionText: String, keyText: String, route: String,
    navController: NavHostController
) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val vchatInfoText = buildAnnotatedString {
            append(questionText)

            pushStringAnnotation(annotation = keyText, tag = route)
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(keyText)
            }
        }
        ClickableText(
            text = vchatInfoText,
            onClick = { position ->
                vchatInfoText.getStringAnnotations(position, position)
                    .firstOrNull { it.tag == route }
                    ?.let {
                        navController.navigate(route)
                    }
            },
            style = TextStyle(color = getSecondAppColor())
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}