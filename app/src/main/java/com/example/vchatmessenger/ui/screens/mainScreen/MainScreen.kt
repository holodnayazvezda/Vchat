package com.example.vchatmessenger.ui.screens.mainScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.vchatmessenger.domain.navigation.MainScreenBottomNavigationItem
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = MainScreenBottomNavigationItem.mainScreenBottomNavigationItems(navController)
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(
        initialPage = 1,
        pageCount = { screens.size }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = getMainAppColor()
            ) {
                for (screenNumber in screens.indices) {
                    val selected = screenNumber == pageState.currentPage
                    val screen = screens[screenNumber]
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            scope.launch {
                                pageState.scrollToPage(screenNumber)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selected) {
                                    screen.selectedIcon
                                } else {
                                    screen.icon
                                },
                                contentDescription = screen.label,
                            )
                        },
                        label = {
                            Text(
                                text = screen.label,
                                fontWeight = if (selected) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Normal
                                }
                            )
                        },
                        colors = NavigationBarItemColors(
                            unselectedIconColor = getSecondAppColor(),
                            unselectedTextColor = getSecondAppColor(),
                            selectedIconColor = getMainAppColor(),
                            selectedTextColor = getSecondAppColor(),
                            selectedIndicatorColor = getSecondAppColor(),
                            disabledIconColor = getSecondAppColor(),
                            disabledTextColor = getSecondAppColor()
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            state = pageState,
            contentPadding = paddingValues
        ) { screenNumber ->
            screens[screenNumber].composable()
        }
    }
}

@Preview
@Composable
private fun MainScreenPrev() {
    MainScreen()
}