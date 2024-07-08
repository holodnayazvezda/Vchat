package com.example.vchatmessenger.ui.screens.viewSecretKey

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.domain.usecase.viewSecretKey.ViewSecretKeyUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel

class ViewSecretKeyViewModel(
    private val sharedVM: SignUpSharedViewModel,
    private val vchatSharedVM: VchatSharedViewModel,
    private val viewSecretKeyUsecase: ViewSecretKeyUsecase,
) : ViewModel() {

    fun buttonNextPressed(navController: NavHostController) {
        vchatSharedVM.writeAuthDataToSharedPreferences(
            sharedVM.data.nickname,
            sharedVM.data.newPassword
        )
        navController.navigate(ScreensRouts.MainScreen.route)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                ViewSecretKeyViewModel(
                    application.container.signUpSharedViewModel,
                    application.container.vchatSharedViewModel,
                    ViewSecretKeyUsecase()
                )
            }
        }
    }
}
