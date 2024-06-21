package com.example.vchatmessenger.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.example.vchatmessenger.data.models.UserAvatar
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor
import com.example.vchatmessenger.ui.theme.getUserAvatarDefaultBackgroundColor

@Composable
fun VchatImagePickerAlertDialog(
    sharedVM: SignUpSharedViewModel,
    closeRequest: () -> Unit,
) {
    val context = LocalContext.current
    val mainAppColorInt = getMainAppColor().toArgb()
    val secondAppColorInt = getSecondAppColor().toArgb()
    val imageCropLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            val resultBitmap = getBitmapFromUri(
                context,
                result.uriContent!!
            )
            sharedVM.changeUserAvatar(
                userAvatar = UserAvatar(
                    avatar = resultBitmap,
                    currentBackgroundColor = getUserAvatarDefaultBackgroundColor(),
                    avatarType = 2
                )
            )
            closeRequest()
        }
    }

    AlertDialog(
        title = {
            Text(
                text = "Загрузить аватар"
            )
        },
        onDismissRequest = { closeRequest() },
        confirmButton = {
            Button(
                onClick = {
                    imageCropLauncher.launch(
                        CropImageContractOptions(
                            null,
                            CropImageOptions(
                                imageSourceIncludeCamera = true,
                                imageSourceIncludeGallery = false,
                                cropShape = CropImageView.CropShape.OVAL,
                                outputCompressQuality = 100,
                                activityBackgroundColor = mainAppColorInt,
                                toolbarColor = mainAppColorInt,
                                toolbarBackButtonColor = secondAppColorInt,
                                activityMenuIconColor = secondAppColorInt,
                                activityMenuTextColor = secondAppColorInt,
                                progressBarColor = mainAppColorInt
                            )
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = getSecondAppColor(), contentColor = getMainAppColor())
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.PhotoCamera,
                        contentDescription = "иконка камеры"
                    )
                    Spacer(modifier = Modifier.padding(end = 5.dp))
                    Text(
                        text = "Камера",
                        fontSize = 15.sp
                    )
                }
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    imageCropLauncher.launch(
                        CropImageContractOptions(
                            null,
                            CropImageOptions(
                                imageSourceIncludeGallery = true,
                                imageSourceIncludeCamera = false,
                                cropShape = CropImageView.CropShape.OVAL,
                                outputCompressQuality = 100,
                                activityBackgroundColor = mainAppColorInt,
                                toolbarColor = mainAppColorInt,
                                toolbarBackButtonColor = secondAppColorInt,
                                activityMenuIconColor = secondAppColorInt,
                                activityMenuTextColor = secondAppColorInt,
                                progressBarColor = mainAppColorInt
                            )
                        )
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = getSecondAppColor(), contentColor = getMainAppColor())
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Filled.PhotoLibrary,
                        contentDescription = "иконка галлереи"
                    )
                    Spacer(modifier = Modifier.padding(end = 5.dp))
                    Text(
                        text = "Галлерея",
                        fontSize = 15.sp
                    )
                }
            }
        },
        containerColor = getMainAppColor(),
        textContentColor = getSecondAppColor(),
        titleContentColor = getSecondAppColor(),
        iconContentColor = getSecondAppColor(),
    )
}

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap {
    return ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
}