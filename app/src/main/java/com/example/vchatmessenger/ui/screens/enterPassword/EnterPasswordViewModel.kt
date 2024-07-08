package com.example.vchatmessenger.ui.screens.enterPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.EnterPasswordState
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.enterPassword.EnterPasswordUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import kotlinx.coroutines.launch

class EnterPasswordViewModel(
    private val sharedVM: LogInSharedViewModel,
    private val vchatSharedVM: VchatSharedViewModel,
    private val enterPasswordUsecase: EnterPasswordUsecase
) : ViewModel() {
    var state by mutableStateOf(EnterPasswordState())
        private set

    fun updateData(
        password: String = "",
        showErrorDialog: Boolean = false
    ) {
        state = state.copy(
            password = password,
            showErrorDialog = showErrorDialog
        )
        sharedVM.changePassword(password = password)
    }

    private suspend fun checkPassword(nickname: String) {
        state = state.copy(
            isLoading = true
        )
        state = try {
            val errorPassword = enterPasswordUsecase.checkPassword(nickname, state.password)
            state.copy(
                errorPassword = errorPassword,
                isLoading = false,
                showErrorDialog = errorPassword.isNotEmpty()
            )
        } catch (e: Exception) {
            state.copy(
                errorPassword = ErrorUsecase.getErrorText(e.message),
                isLoading = false,
                showErrorDialog = true
            )
        }
    }

    fun buttonNextPressed(navController: NavHostController) {
        viewModelScope.launch {
            checkPassword(sharedVM.data.nickname)
            if (state.errorPassword.isEmpty()) {
                vchatSharedVM.writeAuthDataToSharedPreferences(
                    nickname = sharedVM.data.nickname,
                    password = sharedVM.data.password
                )
                navController.navigate(ScreensRouts.MainScreen.route)
            } else {
                state = state.copy(showErrorDialog = true)
            }
        }
    }

    private fun restorePreviouslyEnteredData() {
        if (sharedVM.data.password.isNotEmpty()) {
            updateData(password = sharedVM.data.password)
        }
    }

    init {
        restorePreviouslyEnteredData()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                val vchatRepository = application.container.vchatRepository
                EnterPasswordViewModel(
                    sharedVM = application.container.logInSharedViewModel,
                    vchatSharedVM = application.container.vchatSharedViewModel,
                    enterPasswordUsecase = EnterPasswordUsecase(repository = vchatRepository)
                )
            }
        }
    }
}