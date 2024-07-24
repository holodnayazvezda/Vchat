package com.example.vchatmessenger.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.vchatmessenger.data.storage.AuthStorage
import com.example.vchatmessenger.domain.navigation.NavGraph
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.ui.theme.VchatMessengerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val application = context.applicationContext as VchatApplication
            val authStorage = AuthStorage(context)
            VchatMessengerTheme {
                NavGraph(
                    signUpSharedViewModel = application.container.signUpSharedViewModel,
                    logInSharedViewModel = application.container.logInSharedViewModel,
                    vchatSharedViewModel = application.container.vchatSharedViewModel,
                    startScreen = if (authStorage.isStartup != null) {
                        ScreensRouts.MainScreen.route
                    } else {
                        ScreensRouts.Welcome.route
                    }
                )
            }
        }
    }
}
