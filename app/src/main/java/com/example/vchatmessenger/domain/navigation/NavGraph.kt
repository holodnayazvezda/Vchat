package com.example.vchatmessenger.domain.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vchatmessenger.domain.usecase.checkPassword.CreatePasswordUsecase
import com.example.vchatmessenger.domain.usecase.enterSecretKey.EnterSecretKeyUsecase
import com.example.vchatmessenger.ui.components.ImagePicker
import com.example.vchatmessenger.ui.screens.createPassword.CreatePasswordScreen
import com.example.vchatmessenger.ui.screens.createPassword.CreatePasswordViewModel
import com.example.vchatmessenger.ui.screens.enterPassword.EnterPasswordScreen
import com.example.vchatmessenger.ui.screens.enterPassword.EnterPasswordViewModel
import com.example.vchatmessenger.ui.screens.enterSecretKey.EnterSecretKeyScreen
import com.example.vchatmessenger.ui.screens.enterSecretKey.EnterSecretKeyViewModel
import com.example.vchatmessenger.ui.screens.login.LogInViewModel
import com.example.vchatmessenger.ui.screens.login.LoginScreen
import com.example.vchatmessenger.ui.screens.signup.SignUpScreen
import com.example.vchatmessenger.ui.screens.signup.SignUpViewModel
import com.example.vchatmessenger.ui.screens.viewSecretKey.ViewSecretKeyScreen
import com.example.vchatmessenger.ui.screens.viewSecretKey.ViewSecretKeyViewModel
import com.example.vchatmessenger.ui.screens.welcome.WelcomeScreen
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel

@Composable
fun NavGraph(
    signUpSharedViewModel: SignUpSharedViewModel,
    logInSharedViewModel: LogInSharedViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("signup") {
            SignUpScreen(
                viewModel(factory = SignUpViewModel.Factory),
                signUpSharedViewModel,
                navController
            )
        }
        composable("login") {
            LoginScreen(
                viewModel(factory = LogInViewModel.Factory),
                logInSharedViewModel,
                navController
            )
        }
        composable("create_password") {
            CreatePasswordScreen(
                CreatePasswordViewModel(CreatePasswordUsecase()),
                signUpSharedViewModel,
                navController
            )
        }
        composable("enter_password") {
            EnterPasswordScreen(
                viewModel(factory = EnterPasswordViewModel.Factory),
                logInSharedViewModel,
                navController
            )
        }
        composable("enter_secret_key") {
            EnterSecretKeyScreen(
                EnterSecretKeyViewModel(
                    logInSharedViewModel,
                    EnterSecretKeyUsecase()
                ),
                navController
            )
        }
        composable("view_secret_key") {
            ViewSecretKeyScreen(
                viewModel(factory = ViewSecretKeyViewModel.Factory),
                signUpSharedViewModel,
                navController
            )
        }
        composable("image_picker") {
            ImagePicker()
        }
    }
}