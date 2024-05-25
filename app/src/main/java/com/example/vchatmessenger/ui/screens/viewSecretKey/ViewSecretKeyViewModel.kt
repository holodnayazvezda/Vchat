package com.example.vchatmessenger.ui.screens.viewSecretKey

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.viewSecretKey.ViewSecretKeyUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import kotlinx.coroutines.launch

class ViewSecretKeyViewModel(
    private val viewSecretKeyUsecase: ViewSecretKeyUsecase
) : ViewModel() {

    var state by mutableStateOf(SecretKeyState())
        private set

    fun updateData(
        secretKey: List<String> = listOf("mother", "father", "granny", "ded", "polina"),
        showErrorDialog: Boolean = false
    ) {
        state = state.copy(
            secretKey = secretKey,
            showErrorDialog = showErrorDialog
        )
    }

    private suspend fun createUser(sharedVM: SignUpSharedViewModel) {
        state = state.copy(
            isLoading = true
        )
        state = try {
            val registeredUser = viewSecretKeyUsecase.createUser(sharedVM.data)
            state.copy(
                isLoading = false
            )
        } catch (e: Exception) {
            state.copy(
                isLoading = false,
                showErrorDialog = true,
                error = ErrorUsecase.getErrorText(e.message),
            )
        }
    }

    fun buttonNextPressed(sharedVM: SignUpSharedViewModel, navController: NavHostController) {
        viewModelScope.launch {
            createUser(sharedVM)
            if (!state.showErrorDialog) {
                navController.navigate("image_picker")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                val vchatRepository = application.container.vchatRepository
                ViewSecretKeyViewModel(ViewSecretKeyUsecase(repository = vchatRepository))
            }
        }
    }
}

data class SecretKeyState(
    val secretKey: List<String> = listOf("mother", "father", "granny", "ded", "polina"),
    val isLoading: Boolean = false,
    val showErrorDialog: Boolean = false,
    val error: String = ""
)