package com.sb.camerax.ui.sceen

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraViewScreen() {
    val context = LocalContext.current
    val previewView = remember {
        PreviewView(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView(
            factory = {
                previewView
            }
        )
    }
}