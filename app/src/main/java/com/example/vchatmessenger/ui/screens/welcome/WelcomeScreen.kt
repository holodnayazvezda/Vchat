package com.example.vchatmessenger.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vchatmessenger.R
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.ui.components.VchatInfoText
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun WelcomeScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getMainAppColor())
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(190.dp))
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold,
                color = getSecondAppColor(),
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.welcome_text),
                fontSize = 22.sp,
                color = getSecondAppColor(),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(260.dp))
            Button(
                onClick = {
                    navController.navigate(ScreensRouts.SignUp.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = getSecondAppColor())
            ) {
                Text(
                    text = stringResource(id = R.string.registration_text),
                    fontSize = 22.sp,
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 8.dp, bottom = 8.dp),
                    color = getMainAppColor()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            VchatInfoText(
                "Уже зарегистрировались? ",
                "Войти",
                ScreensRouts.LogIn.route,
                navController
            )
        }
    }
}

@Preview
@Composable
private fun WelcomeScreenPrev() {
    WelcomeScreen(
        rememberNavController()
    )
}