package com.example.vchatmessenger.domain.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.vchatmessenger.ui.screens.allChats.AllChatsScreen
import com.example.vchatmessenger.ui.screens.privateChats.PrivateChatsScreen
import com.example.vchatmessenger.ui.screens.profile.ProfileScreen

data class MainScreenBottomNavigationItem(
    val label : String,
    val selectedIcon : ImageVector,
    val icon : ImageVector,
    val route : String,
    val page: Int,
    val composable: @Composable () -> Unit
) {
    companion object {
        fun mainScreenBottomNavigationItems(navController: NavHostController): List<MainScreenBottomNavigationItem> {
            return listOf(
                MainScreenBottomNavigationItem(
                    label = "Профиль",
                    selectedIcon = Icons.Filled.AccountCircle,
                    icon = Icons.Outlined.AccountCircle,
                    route = ScreensRouts.Profile.route,
                    page = 0,
                    composable = { ProfileScreen(navController = navController) }
                ),
                MainScreenBottomNavigationItem(
                    label = "Все чаты",
                    selectedIcon = Icons.AutoMirrored.Filled.Chat,
                    icon = Icons.AutoMirrored.Outlined.Chat,
                    route = ScreensRouts.AllChats.route,
                    page = 1,
                    composable = { AllChatsScreen(navController = navController) }
                ),
                MainScreenBottomNavigationItem(
                    label = "Личные",
                    selectedIcon = Icons.Filled.People,
                    icon = Icons.Outlined.PeopleOutline,
                    route = ScreensRouts.PrivateChats.route,
                    page = 2,
                    composable = { PrivateChatsScreen(navController = navController) }
                )
            )
        }
    }
}
