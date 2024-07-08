package com.example.vchatmessenger.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.LogInState
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.login.LogInUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import kotlinx.coroutines.launch

class LogInViewModel(
    private val sharedVM: LogInSharedViewModel,
    private val logInUsecase: LogInUsecase
): ViewModel() {
    var state by mutableStateOf(LogInState())
        private set

    fun updateData(
        nickname: String = "",
        showErrorDialog: Boolean = false
    ) {
        state = state.copy(
            nickname = nickname,
            showErrorDialog = showErrorDialog
        )
        sharedVM.changeNickname(nickname = state.nickname)
    }

    private suspend fun checkNickname() {
        state = state.copy(
            isLoading = true
        )
        state = try {
            val errorNickname = logInUsecase.checkNickname(state.nickname)
            state.copy(
                errorNickname = errorNickname,
                isLoading = false,
                showErrorDialog = errorNickname.isNotEmpty()
            )
        } catch (e: Exception) {
            state.copy(
                errorNickname = ErrorUsecase.getErrorText(e.message),
                isLoading = false,
                showErrorDialog = true
            )
        }
    }

    fun buttonNextPressed(navController: NavHostController) {
        viewModelScope.launch {
            checkNickname()
            if (state.errorNickname.isEmpty()) {
                navController.navigate(ScreensRouts.EnterPassword.route)
            } else {
                state = state.copy(showErrorDialog = true)
            }
        }
    }

    private fun restorePreviouslyEnteredData() {
        updateData(
            nickname = sharedVM.data.nickname
        )
    }

    init {
        restorePreviouslyEnteredData()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                val vchatRepository = application.container.vchatRepository
                LogInViewModel(
                    application.container.logInSharedViewModel,
                    LogInUsecase(repository = vchatRepository)
                )
            }
        }
    }
}
