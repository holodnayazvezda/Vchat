package com.example.vchatmessenger.ui.screens.enterSecretKey

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vchatmessenger.R
import com.example.vchatmessenger.domain.usecase.enterSecretKey.EnterSecretKeyUsecase
import com.example.vchatmessenger.ui.components.VchatAlertDialog
import com.example.vchatmessenger.ui.components.VchatBackIconButton
import com.example.vchatmessenger.ui.components.VchatInfoText
import com.example.vchatmessenger.ui.components.VchatNextFloatingActionButton
import com.example.vchatmessenger.ui.components.VchatSecretKeyInputBox
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun EnterSecretKeyScreen(
    vm: EnterSecretKeyViewModel,
    navController: NavHostController
) {
    val state = vm.state
    val openErrorDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getMainAppColor())
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.85f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                VchatBackIconButton("enter_password", navController)
                Spacer(modifier = Modifier.height(50.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.password_recovery),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = getSecondAppColor(),
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp
                    )
                    Spacer(modifier = Modifier.height(70.dp))
                    val numbers = listOf(
                        0, 1, 2
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(items = numbers) { number ->
                            VchatSecretKeyInputBox(
                                number = number + 1,
                                secretKey = state.secretKey[number],
                                onSecretKeyChange = { secretKey -> vm.updateSecretKey(number, secretKey) }
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.15f),
            contentAlignment = Alignment.BottomEnd
        ) {
            VchatNextFloatingActionButton {
                if (state.errorSecretKey.isNotEmpty()) {
                    openErrorDialog.value = true
                } else {
                    navController.navigate("enter_password")
                }
            }
        }
        VchatInfoText(
            "Вспомнили пароль? ",
            "Войти",
            "enter_password",
            navController
        )

        when {
            openErrorDialog.value -> {
                VchatAlertDialog(
                    onDismissRequest = { openErrorDialog.value = false },
                    onConfirmation = {
                        openErrorDialog.value = false
                    },
                    dialogTitle = "Произошла ошибка",
                    dialogText = state.errorSecretKey
                )
            }
        }
    }
}

@Preview
@Composable
private fun EnterSecretKeyScreenPrev() {
    EnterSecretKeyScreen(
        EnterSecretKeyViewModel(
            LogInSharedViewModel(),
            EnterSecretKeyUsecase()
        ),
        rememberNavController()
    )
}