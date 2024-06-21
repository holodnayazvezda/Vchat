package com.example.vchatmessenger.ui.screens.enterSecretKey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.EnterSecretKeyState
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.enterSecretKey.EnterSecretKeyUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import kotlinx.coroutines.launch

class EnterSecretKeyViewModel(
    private val sharedVM: LogInSharedViewModel,
    private val vchatSharedVM: VchatSharedViewModel,
    private val enterSecretKeyUsecase: EnterSecretKeyUsecase
): ViewModel() {
    var state by mutableStateOf(EnterSecretKeyState())
        private set

    fun setShowErrorDialog(showErrorDialog: Boolean) {
        state = state.copy(
            showErrorDialog = showErrorDialog
        )
    }

    fun updateSecretKey(secretKeyWordNumber: Int, secretKeyWord: String) {
        val secretKeys = state.secretKey.toMutableList()
        secretKeys[secretKeyWordNumber] = secretKeyWord
        state = state.copy(
            secretKey = secretKeys
        )
        sharedVM.updateSecretKey(secretKeyWordNumber, secretKeyWord)
    }

    private fun setSecretKeyWordsNumbers() {
        val secretKeyWordsNumbers = enterSecretKeyUsecase.generateSecretKeyWordsNumbers()
        sharedVM.changeSecretKeyWordsNumbers(secretKeyWordsNumbers)
    }

    private suspend fun checkSecretKey() {
        state = state.copy(
            isLoading = true
        )
        state = try {
            val errorSecretKey = enterSecretKeyUsecase.checkSecretKey(
                sharedVM.data.nickname,
                sharedVM.data.secretKeyWordsNumbers,
                sharedVM.data.secretKey
            )
            state.copy(
                errorSecretKey = errorSecretKey,
                isLoading = false,
                showErrorDialog = errorSecretKey.isNotEmpty()
            )
        } catch (e: Exception) {
            state.copy(
                errorSecretKey = ErrorUsecase.getErrorText(e.message),
                isLoading = false,
                showErrorDialog = true
            )
        }
    }

    fun buttonNextPressed(navController: NavHostController) {
        viewModelScope.launch {
            checkSecretKey()
            if (state.errorSecretKey.isEmpty()) {
                vchatSharedVM.displayCreatePasswordScreenAsCreateNewPasswordScreen = true
                navController.navigate("create_password")
            } else {
                setShowErrorDialog(true)
            }
        }
    }

    private fun restorePreviouslyEnteredData() {
        state = state.copy(
            secretKeyWordsNumbers = sharedVM.data.secretKeyWordsNumbers,
            secretKey = sharedVM.data.secretKey,
        )
    }

    init {
        if (sharedVM.data.secretKeyWordsNumbers.sum() == 0) {
            setSecretKeyWordsNumbers()
        }
        restorePreviouslyEnteredData()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                val vchatRepository = application.container.vchatRepository
                EnterSecretKeyViewModel(
                    application.container.logInSharedViewModel,
                    application.container.vchatSharedViewModel,
                    EnterSecretKeyUsecase(vchatRepository)
                )
            }
        }
    }
}
