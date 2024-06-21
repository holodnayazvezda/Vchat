package com.example.vchatmessenger.ui.components

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.vchatmessenger.ui.screens.chooseAvatar.ChooseAvatarViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoundedImage(
    bitmap: Bitmap,
    size: Dp,
    vm: ChooseAvatarViewModel,
    sharedVM: SignUpSharedViewModel
) {
    var showGalleryDialog by remember {
        mutableStateOf(false)
    }

    var showDeleteSelectedAvatarDialog by remember {
        mutableStateOf(false)
    }

    AnimatedContent(
        targetState = bitmap,
        transitionSpec = {
            if (targetState != initialState) {
                (scaleIn() + fadeIn())
                    .togetherWith(scaleOut() + fadeOut())
            } else {
                (scaleIn() + fadeIn())
                    .togetherWith(scaleOut() + fadeOut())
            }.using(
                SizeTransform(clip = false)
            )
        },
        label = "AnimatedUserAvatar"
    ) {userAvatar ->
        Image(
            bitmap = userAvatar.asImageBitmap(),
            contentDescription = "ава пользователя",
            modifier = Modifier
                .size(size)
                .clip(shape = RoundedCornerShape(100.dp))
                .combinedClickable(
                    onClick = {
                        if (sharedVM.data.userAvatar.avatarType == 1) {
                            vm.generateAvatar(sharedVM.data.name)
                        } else {
                            showGalleryDialog = true
                        }
                    },
                    onLongClick = {
                        if (sharedVM.data.userAvatar.avatarType == 1) {
                            showGalleryDialog = true
                        } else {
                            showDeleteSelectedAvatarDialog = true
                        }
                    }
                ),
            contentScale = ContentScale.Crop
        )
    }

    when {
        showGalleryDialog -> {
            VchatImagePickerAlertDialog(
                closeRequest = {showGalleryDialog = false},
                sharedVM = sharedVM
            )
        }
    }

    when {
        showDeleteSelectedAvatarDialog -> {
            VchatDeleteSelectedAvatarAlertDialog(
                onDismissRequest = { showDeleteSelectedAvatarDialog = false },
                onConfirmation = {
                    showDeleteSelectedAvatarDialog = false
                    vm.generateAvatar(sharedVM.data.name)
                }
            )
        }
    }
}
