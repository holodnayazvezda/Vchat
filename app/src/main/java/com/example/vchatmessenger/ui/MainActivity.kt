package com.example.vchatmessenger.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.domain.navigation.NavGraph
import com.example.vchatmessenger.ui.theme.VchatMessengerTheme

class MainActivity : ComponentActivity() {
    private val signUpSharedViewModel : SignUpSharedViewModel by viewModels()
    private val logInSharedViewModel : LogInSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VchatMessengerTheme {
                NavGraph(
                    signUpSharedViewModel,
                    logInSharedViewModel
                )
            }
        }
    }
}
