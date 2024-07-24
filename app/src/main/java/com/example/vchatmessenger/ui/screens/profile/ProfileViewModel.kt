package com.example.vchatmessenger.ui.screens.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vchatmessenger.data.network.ImageLoader
import com.example.vchatmessenger.data.states.ProfileState
import com.example.vchatmessenger.data.storage.AuthStorage
import com.example.vchatmessenger.data.storage.MainStorage
import com.example.vchatmessenger.domain.usecase.avatar.AvatarUsecase
import com.example.vchatmessenger.domain.usecase.chooseAvatar.ColorsWorker
import com.example.vchatmessenger.domain.usecase.profile.ProfileUsecase
import com.example.vchatmessenger.ui.VchatApplication
import kotlinx.coroutines.launch


class ProfileViewModel(
    private val mainStorage: MainStorage,
    private val profileUsecase: ProfileUsecase,
): ViewModel() {
    var state by mutableStateOf(ProfileState())
        private set

    private fun loadUserFromStorage() {
        val user = mainStorage.user
        if (user != null) {
            state = state.copy(
                user = user
            )
        }
    }

    private fun setInitialUserAvatar() {
        val user = state.user
        state = if (user.avatar.avatarType == 1) {
            state.copy(
                userAvatar = AvatarUsecase.generateAvatar(
                    name = user.name,
                    currentBackgroundColor = ColorsWorker.getColorFromString(user.avatar.avatarBackgroundColor)
                )
            )
        } else {
            state.copy(
                userAvatar = AvatarUsecase.generateAvatar(user.name)
            )
        }
    }

    private suspend fun loadUsersAvatar() {
        val user = mainStorage.user
        if (user != null && user.avatar.avatarType == 2) {
            try {
                state = state.copy(
                    userAvatar = profileUsecase.getUserAvatar(user)
                )
            } catch (e: Exception) {
                Log.e("Avatar loading", "Unable to load avatar: error ${e.message}")
            }
        }
    }

    init {
        viewModelScope.launch {
            loadUserFromStorage()
            setInitialUserAvatar()
            loadUsersAvatar()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as VchatApplication)

                val mainStorage = MainStorage(application)
                val authStorage = AuthStorage(application)

                val profileUsecase = ProfileUsecase(
                    ImageLoader(
                        context = application,
                        authStorage = authStorage
                    )
                )

                ProfileViewModel(
                    mainStorage,
                    profileUsecase
                )
            }
        }
    }
}