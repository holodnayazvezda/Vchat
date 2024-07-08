package com.example.vchatmessenger.ui.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.SignUpState
import com.example.vchatmessenger.domain.navigation.ScreensRouts
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.signup.SignUpUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val sharedVM: SignUpSharedViewModel,
    private val vchatSharedVM: VchatSharedViewModel,
    private val signUpUsecase: SignUpUsecase
) : ViewModel() {
    var state by mutableStateOf(SignUpState())
        private set

    fun updateData(
        name: String = state.name,
        nickname: String = state.nickname,
        showErrorDialog: Boolean = state.showErrorDialog
    ) {
        state = state.copy(
            name = name,
            nickname = nickname,
            showErrorDialog = showErrorDialog
        )
        sharedVM.changeName(name = name)
        sharedVM.changeNickname(nickname = nickname)
    }

    private fun checkName() {
        updateData(name = state.name.trim())
        state = state.copy(
            errorName = signUpUsecase.checkName(state.name)
        )
    }

    private suspend fun checkNickname() {
        updateData(nickname = state.nickname.trim())
        if (state.errorName.isEmpty()) {
            state = state.copy(
                isLoading = true
            )
            state = try {
                val errorNickname = signUpUsecase.checkNickname(state.nickname)
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
    }

    fun buttonNextPressed(navController: NavHostController) {
        viewModelScope.launch {
            checkName()
            if (state.errorName.isEmpty()) {
                checkNickname()
            }
            if (state.errorName.isEmpty() && state.errorNickname.isEmpty()) {
                vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen = false
                navController.navigate(ScreensRouts.CreatePassword.route)
            } else {
                state = state.copy(showErrorDialog = true)
            }
        }
    }

    private fun restorePreviouslyEnteredData() {
        updateData(
            name = sharedVM.data.name,
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
                SignUpViewModel(
                    application.container.signUpSharedViewModel,
                    application.container.vchatSharedViewModel,
                    SignUpUsecase(repository = vchatRepository)
                )
            }
        }
    }
}
