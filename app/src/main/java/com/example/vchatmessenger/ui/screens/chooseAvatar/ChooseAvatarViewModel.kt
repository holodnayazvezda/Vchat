package com.example.vchatmessenger.ui.screens.chooseAvatar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.vchatmessenger.data.states.ChooseAvatarState
import com.example.vchatmessenger.domain.usecase.ErrorUsecase
import com.example.vchatmessenger.domain.usecase.chooseAvatar.ChooseAvatarUsecase
import com.example.vchatmessenger.ui.VchatApplication
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import kotlinx.coroutines.launch

class ChooseAvatarViewModel(
    private val sharedVM: SignUpSharedViewModel,
    private val chooseAvatarUsecase: ChooseAvatarUsecase
): ViewModel() {
    var state by mutableStateOf(ChooseAvatarState())
        private set

    fun setShowErrorDialog(showErrorDialog: Boolean) {
        state = state.copy(
            showErrorDialog = showErrorDialog
        )
    }

    fun generateAvatar(name: String) {
        val generatedAvatar = chooseAvatarUsecase.generateAvatar(
            name,
            sharedVM.data.userAvatar.currentBackgroundColor
        )
        sharedVM.changeUserAvatar(userAvatar = generatedAvatar)
    }

    fun getAvatarHelpText(): String {
        return if (sharedVM.data.userAvatar.avatarType == 1) {
            "Нажмите на сгенерированный аватар, чтобы изменить цвет фона. Удерживайте для загрузки аватара из галлереи или камеры"
        } else {
            "Нажмите на загруженный аватар, чтобы загрузить новый. Удерживайте для его удаления"
        }
    }

    private suspend fun createUser() {
        state = state.copy(
            isLoading = true
        )
        state = try {
            val registeredUserSecretKey = chooseAvatarUsecase.createUser(sharedVM.data)
            var errorText = ""
            if (registeredUserSecretKey.isEmpty()) {
                errorText = "Неизвестная ошибка"
            }
            sharedVM.changeSecretKey(secretKey = registeredUserSecretKey)
            state.copy(
                isLoading = false,
                showErrorDialog = registeredUserSecretKey.isEmpty(),
                error = errorText,
            )
        } catch (e: Exception) {
            state.copy(
                isLoading = false,
                showErrorDialog = true,
                error = ErrorUsecase.getErrorText(e.message)
            )
        }
    }

    fun buttonNextPressed(navController: NavHostController) {
        viewModelScope.launch {
            createUser()
            if (state.error.isEmpty()) {
                navController.navigate("view_secret_key")
            }
        }
    }

    init {
        if (sharedVM.data.userAvatar.avatar == null) {
            generateAvatar(sharedVM.data.name)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)
                val vchatRepository = application.container.vchatRepository
                ChooseAvatarViewModel(
                    application.container.signUpSharedViewModel,
                    ChooseAvatarUsecase(repository = vchatRepository)
                )
            }
        }
    }
}