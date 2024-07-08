package com.example.vchatmessenger.ui.screens.createPassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.CreatePasswordState
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.checkPassword.CreatePasswordUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import kotlinx.coroutines.launch

class CreatePasswordViewModel(
    private val signUpSharedVM: SignUpSharedViewModel,
    private val logInSharedVM: LogInSharedViewModel,
    private val vchatSharedVM: VchatSharedViewModel,
    private val createPasswordUsecase: CreatePasswordUsecase
) : ViewModel() {
    var state by mutableStateOf(CreatePasswordState())
        private set

    fun updatePassword(password: String) {
        state = state.copy(
            password = password,
            errorPassword = createPasswordUsecase.checkEverything(password, state.confirmPassword)
        )
        signUpSharedVM.changePassword(
            password = password,
            onUpdate = vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen
        )
    }

    fun updateConfirmPassword(confirmPassword: String) {
        state = state.copy(
            confirmPassword = confirmPassword,
            errorPassword = createPasswordUsecase.checkEverything(state.password, confirmPassword)
        )
        signUpSharedVM.changeConfirmPassword(
            confirmPassword = confirmPassword,
            onUpdate = vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen
        )
    }

    fun setShowErrorDialog(
        showErrorDialog: Boolean = false
    ) {
        state = state.copy(
            showErrorDialog = showErrorDialog
        )
    }

    private suspend fun changePasswordUsingSecretKey() {
        state = state.copy(
            isLoading = true
        )
        state = try {
            createPasswordUsecase.changePasswordUsingSecretKey(
                logInSharedVM.data.nickname,
                logInSharedVM.data.secretKeyWordsNumbers,
                logInSharedVM.data.secretKey,
                state.password
            )
            state.copy(
                errorPassword = "",
                isLoading = false,
                showErrorDialog = false
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
        if (state.errorPassword.isNotEmpty()) {
            setShowErrorDialog(true)
        } else {
            if (vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen) {
                viewModelScope.launch {
                    changePasswordUsingSecretKey()
                    if (state.errorPassword.isEmpty()) {
                        vchatSharedVM.writeAuthDataToSharedPreferences(
                            nickname = logInSharedVM.data.nickname,
                            password = logInSharedVM.data.password
                        )
                        navController.navigate(ScreensRouts.MainScreen.route)
                    } else {
                        state = state.copy(showErrorDialog = true)
                    }
                }
            } else {
                navController.navigate(ScreensRouts.ChooseAvatar.route)
            }
        }
    }

    private fun restorePreviouslyEnteredData() {
        updatePassword(
            password = if (vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen) {
                signUpSharedVM.data.newPassword
            } else {
                signUpSharedVM.data.confirmPassword
            }
        )
        updateConfirmPassword(
            confirmPassword = if (vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen) {
                signUpSharedVM.data.newConfirmPassword
            } else {
                signUpSharedVM.data.confirmPassword
            }
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
                CreatePasswordViewModel(
                    application.container.signUpSharedViewModel,
                    application.container.logInSharedViewModel,
                    application.container.vchatSharedViewModel,
                    CreatePasswordUsecase(vchatRepository)
                )
            }
        }
    }
}