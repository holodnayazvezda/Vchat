package com.example.vchatmessenger.ui.screens.viewSecretKey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.domain.usecase.viewSecretKey.ViewSecretKeyUsecase
import com.example.vchatmessenger.ui.VchatApplication

class ViewSecretKeyViewModel(
    private val viewSecretKeyUsecase: ViewSecretKeyUsecase
) : ViewModel() {

    fun buttonNextPressed(navController: NavHostController) {
        navController.navigate("welcome")
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                ViewSecretKeyViewModel(ViewSecretKeyUsecase())
            }
        }
    }
}
