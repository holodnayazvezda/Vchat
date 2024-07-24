package com.example.vchatmessenger.ui.screens.mainScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.MainScreenState
import com.example.vchatmessenger.data.storage.AuthStorage
import com.example.vchatmessenger.data.storage.MainStorage
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.mainScreen.MainScreenUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val vchatSharedVM: VchatSharedViewModel,
    private val mainScreenUsecase: MainScreenUsecase,
    private val mainStorage: MainStorage,
    private val authStorage: AuthStorage
): ViewModel() {
    var state by mutableStateOf(MainScreenState())
        private set

    fun errorDialogOnConfirmation(navController: NavHostController) {
        if (state.logOutAfterConfirmingInErrorDialog) {
            state = state.copy(showErrorDialog = false)
            vchatSharedVM.logOut(navController)
        } else {
            state = state.copy(showErrorDialog = false)
        }
    }

    fun errorDialogOnDismissing() {
        if (!state.logOutAfterConfirmingInErrorDialog) {
            state = state.copy(showErrorDialog = false)
        }
    }

    private suspend fun checkAuthCredentialsAndGetUser() {
        val nickname = authStorage.nickname
        val password = authStorage.password
        if (nickname.isNullOrEmpty() || password.isNullOrEmpty()) {
            state = state.copy(
                errorCredentials = "Авторизационные данные устарели! Попробуйте войти заново.",
                logOutAfterConfirmingInErrorDialog = true
            )
        } else {
            state = state.copy(
                isLoading = true
            )
            try {
                val user = mainScreenUsecase.authorizeUser(nickname, password)
                state = state.copy(
                    errorCredentials = ErrorUsecase.getErrorText(user),
                    isLoading = false,
                    logOutAfterConfirmingInErrorDialog = user == null
                )
                if (user != null) {
                    mainStorage.user = user
                }
            } catch (e: Exception) {
                state = state.copy(
                    errorCredentials = ErrorUsecase.getErrorText(
                        errorMessage = e.message,
                        mainScreen = true
                    ),
                    isLoading = false,
                    logOutAfterConfirmingInErrorDialog = ErrorUsecase.getLogOutAfterPressingOKInErrorDialog(e.message)
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            checkAuthCredentialsAndGetUser()
            if (state.errorCredentials.isNotEmpty()) {
                state = state.copy(showErrorDialog = true)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                val vchatRepository = application.container.vchatRepository

                val authStorage = AuthStorage(application)
                val mainStorage = MainStorage(application)

                MainScreenViewModel(
                    vchatSharedVM = application.container.vchatSharedViewModel,
                    mainScreenUsecase = MainScreenUsecase(vchatRepository),
                    mainStorage = mainStorage,
                    authStorage = authStorage
                )
            }
        }
    }
}