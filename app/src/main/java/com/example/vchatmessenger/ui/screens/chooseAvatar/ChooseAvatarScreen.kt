package com.example.vchatmessenger.ui.screens.chooseAvatar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
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
import com.example.vchatmessenger.domain.usecase.chooseAvatar.ChooseAvatarUsecase
import com.example.vchatmessenger.ui.components.RoundedImage
import com.example.vchatmessenger.ui.components.VchatAlertDialog
import com.example.vchatmessenger.ui.components.VchatBackIconButton
import com.example.vchatmessenger.ui.components.VchatLoadingScreen
import com.example.vchatmessenger.ui.components.VchatNextFloatingActionButton
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor

@Composable
fun ChooseAvatarScreen(
    vm: ChooseAvatarViewModel,
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
                    .fillMaxWidth()
                    .weight(0.35f)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    VchatBackIconButton("create_password", navController)
                    Spacer(modifier = Modifier.height(50.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.choose_your_photo),
                            fontSize = 50.sp,
                            fontWeight = FontWeight.Bold,
                            color = getSecondAppColor(),
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            lineHeight = 40.sp
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    sharedVM.data.userAvatar.avatar?.let {
                        RoundedImage(
                            bitmap = it,
                            size = 200.dp,
                            vm = vm,
                            sharedVM = sharedVM
                        )
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    AnimatedContent(
                        targetState = vm.getAvatarHelpText(),
                        transitionSpec = {
                            if (targetState != initialState) {
                                (scaleIn() + fadeIn())
                                    .togetherWith(scaleOut() + fadeOut())
                            } else {
                                (scaleIn() + fadeIn())
                                    .togetherWith(scaleOut() + fadeOut())
                            }.using(
                                SizeTransform(clip = false)
                            )
                        },
                        label = "AnimatedAvatarHelpText"
                    ) { avatarHelpText ->
                        Text(
                            text = avatarHelpText,
                            color = getSecondAppColor(),
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                        )
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
                    vm.buttonNextPressed(navController)
                }
            }
            when {
                state.showErrorDialog -> {
                    VchatAlertDialog(
                        onDismissRequest = { vm.setShowErrorDialog(false) },
                        onConfirmation = { vm.setShowErrorDialog(false) },
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
private fun ChooseAvatarScreenPreview() {
    ChooseAvatarScreen(
        ChooseAvatarViewModel(
            SignUpSharedViewModel(),
            ChooseAvatarUsecase(null)
        ),
        SignUpSharedViewModel(),
        rememberNavController()
    )
}