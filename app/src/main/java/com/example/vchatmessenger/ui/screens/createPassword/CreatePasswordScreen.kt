package com.example.vchatmessenger.ui.screens.createPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.vchatmessenger.domain.usecase.checkPassword.CreatePasswordUsecase
import com.example.vchatmessenger.ui.components.VchatAlertDialog
import com.example.vchatmessenger.ui.components.VchatBackIconButton
import com.example.vchatmessenger.ui.components.VchatNextFloatingActionButton
import com.example.vchatmessenger.ui.components.VchatOutlinedTextFieldWithHideButton
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun CreatePasswordScreen(
    vm: CreatePasswordViewModel,
    sharedVM: SignUpSharedViewModel,
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
                .fillMaxWidth()
                .weight(0.85f)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                VchatBackIconButton("signup", navController)
                Spacer(modifier = Modifier.height(50.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.password_name),
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = getSecondAppColor(),
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        lineHeight = 40.sp
                    )
                    Spacer(modifier = Modifier.height(70.dp))
                    VchatOutlinedTextFieldWithHideButton(
                        value = state.password,
                        onValueChange = {newPassword ->
                            vm.updatePassword(newPassword)},
                        placeholderText = stringResource(id = R.string.password_text_hint)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    VchatOutlinedTextFieldWithHideButton(
                        value = state.confirmPassword,
                        onValueChange = {confirmPassword ->
                            vm.updateConfirmPassword(confirmPassword)},
                        placeholderText = stringResource(id = R.string.password_confirmation_hint)
                    )
                    Spacer(modifier = Modifier.height(50.dp))
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
                if (state.errorPassword.isNotEmpty()) {
                    openErrorDialog.value = true
                } else {
                    sharedVM.changeData(password = state.password)
                    navController.navigate("view_secret_key")
                }
            }
        }

        when {
            openErrorDialog.value -> {
                VchatAlertDialog(
                    onDismissRequest = { openErrorDialog.value = false },
                    onConfirmation = {
                        openErrorDialog.value = false
                    },
                    dialogTitle = "Произошла ошибка",
                    dialogText = state.errorPassword
                )
            }
        }
    }
}


@Preview
@Composable
private fun CreatePasswordScreenPrev() {
    CreatePasswordScreen(
        CreatePasswordViewModel(CreatePasswordUsecase()),
        SignUpSharedViewModel(),
        rememberNavController()
    )
}