package com.example.vchatmessenger.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.vchatmessenger.domain.navigation.NavGraph
import com.example.vchatmessenger.ui.theme.VchatMessengerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val application = context.applicationContext as VchatApplication
            VchatMessengerTheme {
                NavGraph(
                    application.container.signUpSharedViewModel,
                    application.container.logInSharedViewModel,
                    application.container.vchatSharedViewModel
                )
            }
        }
    }
}
