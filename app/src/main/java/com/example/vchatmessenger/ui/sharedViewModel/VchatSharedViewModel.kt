package com.example.vchatmessenger.ui.sharedViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class VchatSharedViewModel: ViewModel() {
    var displayCreatePasswordScreenAsCreateNewPasswordScreen by mutableStateOf(false)
}