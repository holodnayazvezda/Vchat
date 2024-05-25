package com.example.vchatmessenger.ui.screens.viewSecretKey

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
import com.example.vchatmessenger.data.models.SecretKeyModel
import com.example.vchatmessenger.domain.usecase.viewSecretKey.ViewSecretKeyUsecase
import com.example.vchatmessenger.ui.components.VchatAlertDialog
import com.example.vchatmessenger.ui.components.VchatBackIconButton
import com.example.vchatmessenger.ui.components.VchatLoadingScreen
import com.example.vchatmessenger.ui.components.VchatNextFloatingActionButton
import com.example.vchatmessenger.ui.components.VchatSecretKeyBox
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun ViewSecretKeyScreen(
    vm: ViewSecretKeyViewModel,
    sharedVM: SignUpSharedViewModel,
    navController: NavHostController
) {
    val state = vm.state
    if (!state.isLoading) {
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
                    VchatBackIconButton("create_password", navController)
                    Spacer(modifier = Modifier.height(50.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.secret_key_text),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = getSecondAppColor(),
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            lineHeight = 40.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        val items = listOf(
                            SecretKeyModel(1, "mother"),
                            SecretKeyModel(2, "father"),
                            SecretKeyModel(3, "granny"),
                            SecretKeyModel(4, "ded"),
                            SecretKeyModel(5, "polina"),
                        )
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            items(items = items) { item ->
                                VchatSecretKeyBox(
                                    number = item.number,
                                    secretWord = item.secretWord
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
                    sharedVM.changeData(
                        secretKey = listOf(
                            "mother",
                            "father",
                            "granny",
                            "ded",
                            "polina"
                        )
                    )
                    vm.buttonNextPressed(sharedVM, navController)
                }
            }

            when {
                state.showErrorDialog -> {
                    VchatAlertDialog(
                        onDismissRequest = { vm.updateData(showErrorDialog = false) },
                        onConfirmation = { vm.updateData(showErrorDialog = false) },
                        dialogTitle = "Произошла ошибка",
                        dialogText = state.error
                    )
                }
            }
        }
    } else {
        VchatLoadingScreen()
    }
}

@Preview
@Composable
private fun ViewSecretKeyScreenPrev() {
    ViewSecretKeyScreen(
        ViewSecretKeyViewModel(ViewSecretKeyUsecase(null)),
        SignUpSharedViewModel(),
        rememberNavController()
    )
}