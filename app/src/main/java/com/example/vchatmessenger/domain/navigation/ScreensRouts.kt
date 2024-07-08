package com.example.vchatmessenger.domain.navigation

sealed class ScreensRouts(val route: String) {
    data object Welcome : ScreensRouts("Welcome")
    data object LogIn : ScreensRouts("LogIn")
    data object EnterPassword : ScreensRouts("EnterPassword")
    data object EnterSecretKey : ScreensRouts("EnterSecretKey")
    data object SignUp : ScreensRouts("SignUp")
    data object CreatePassword : ScreensRouts("CreatePassword")
    data object ChooseAvatar : ScreensRouts("ChooseAvatar")
    data object ViewSecretKey : ScreensRouts("ViewSecretKey")
    data object MainScreen : ScreensRouts("MainScreen")
    data object AllChats : ScreensRouts("AllChats")
    data object PrivateChats : ScreensRouts("PrivateChats")
    data object Profile : ScreensRouts("Profile")
}