package com.example.vchatmessenger.ui.components


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.example.vchatmessenger.ui.theme.getMainAppColor
import com.example.vchatmessenger.ui.theme.getSecondAppColor
import run.nabla.gallerypicker.picker.result.GalleryContract
import run.nabla.gallerypicker.picker.result.GalleryRequest

@Composable
fun ImagePicker() {
    val contentResolver = LocalContext.current.contentResolver
    val mainAppColor = getMainAppColor()
    val secondAppColor = getSecondAppColor()
    val pickPhotoLauncher = rememberLauncherForActivityResult(
        contract = GalleryContract(),
        onResult = { uri ->
            if (uri != null) {

            }
        }
    )

    SideEffect {
        pickPhotoLauncher.launch(
            GalleryRequest.Builder()
                .setTitle("Выберете фотографию профиля")
                .setTitleSize(25)
                .setBackgroundColor(mainAppColor.value.toLong())
                .setTitleColor(secondAppColor.value.toLong())
                .showExitAction(false)
                .setItemsRoundedCornerSize(5)
                .setGridColumns(4)
                .setPermissionTitle("Access to your photos")
                .setPermissionSecondaryActionTitle("Cancel")
                .setItemMaxHeight(10)
                .build()
        )
    }
}