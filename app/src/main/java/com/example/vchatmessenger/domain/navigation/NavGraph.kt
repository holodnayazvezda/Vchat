package com.example.vchatmessenger.domain.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vchatmessenger.ui.screens.chooseAvatar.ChooseAvatarScreen
import com.example.vchatmessenger.ui.screens.chooseAvatar.ChooseAvatarViewModel
import com.example.vchatmessenger.ui.screens.createPassword.CreatePasswordScreen
import com.example.vchatmessenger.ui.screens.createPassword.CreatePasswordViewModel
import com.example.vchatmessenger.ui.screens.enterPassword.EnterPasswordScreen
import com.example.vchatmessenger.ui.screens.enterPassword.EnterPasswordViewModel
import com.example.vchatmessenger.ui.screens.enterSecretKey.EnterSecretKeyScreen
import com.example.vchatmessenger.ui.screens.enterSecretKey.EnterSecretKeyViewModel
import com.example.vchatmessenger.ui.screens.login.LogInViewModel
import com.example.vchatmessenger.ui.screens.login.LoginScreen
import com.example.vchatmessenger.ui.screens.mainScreen.MainScreen
import com.example.vchatmessenger.ui.screens.signup.SignUpScreen
import com.example.vchatmessenger.ui.screens.signup.SignUpViewModel
import com.example.vchatmessenger.ui.screens.viewSecretKey.ViewSecretKeyScreen
import com.example.vchatmessenger.ui.screens.viewSecretKey.ViewSecretKeyViewModel
import com.example.vchatmessenger.ui.screens.welcome.WelcomeScreen
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel

@Composable
fun NavGraph(
    signUpSharedViewModel: SignUpSharedViewModel,
    logInSharedViewModel: LogInSharedViewModel,
    vchatSharedViewModel: VchatSharedViewModel,

    startScreen: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startScreen) {
        composable(ScreensRouts.Welcome.route) {
            WelcomeScreen(
                navController
            )
        }
        composable(ScreensRouts.SignUp.route) {
            SignUpScreen(
                viewModel(factory = SignUpViewModel.Factory),
                navController
            )
        }
        composable(ScreensRouts.LogIn.route) {
            LoginScreen(
                viewModel(factory = LogInViewModel.Factory),
                navController
            )
        }
        composable(ScreensRouts.CreatePassword.route) {
            CreatePasswordScreen(
                viewModel(factory = CreatePasswordViewModel.Factory),
                vchatSharedViewModel,
                navController
            )
        }
        composable(ScreensRouts.ChooseAvatar.route) {
            ChooseAvatarScreen(
                viewModel(factory = ChooseAvatarViewModel.Factory),
                signUpSharedViewModel,
                navController
            )
        }
        composable(ScreensRouts.EnterPassword.route) {
            EnterPasswordScreen(
                viewModel(factory = EnterPasswordViewModel.Factory),
                navController
            )
        }
        composable(ScreensRouts.EnterSecretKey.route) {
            EnterSecretKeyScreen(
                viewModel(factory = EnterSecretKeyViewModel.Factory),
                navController
            )
        }
        composable(ScreensRouts.ViewSecretKey.route) {
            ViewSecretKeyScreen(
                viewModel(factory = ViewSecretKeyViewModel.Factory),
                signUpSharedViewModel,
                navController
            )
        }

        composable(ScreensRouts.MainScreen.route) {
            MainScreen()
        }
    }
}